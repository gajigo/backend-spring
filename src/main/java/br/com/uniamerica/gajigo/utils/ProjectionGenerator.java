package br.com.uniamerica.gajigo.utils;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectionGenerator {
    public static void main(String[] args) {
        Set<Class> classes = findAllClassesUsingClassLoader("br.com.uniamerica.gajigo.entity");

        for (Class clazz: classes) {
            if (clazz.isEnum()) continue;
            if (Modifier.isAbstract(clazz.getModifiers())) continue;
            if (!clazz.isAnnotationPresent(Entity.class)) continue;

            generateClassProjections(clazz);
        }
    }

    private static boolean isRelation(Field field) {
        return field.isAnnotationPresent(OneToMany.class) ||
                field.isAnnotationPresent(ManyToOne.class) ||
                field.isAnnotationPresent(ManyToMany.class) ||
                field.isAnnotationPresent(OneToOne.class);
    }

    private static String capitalizeName(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static void generateClassProjections(Class clazz) {
        String name = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        String directoryPath = "src/main/java/br/com/uniamerica/gajigo/entity/projection/" + name.toLowerCase();
        File directory = new File(directoryPath);

        for (Field field : fields) {
            if (!isRelation(field)) continue;

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String projectionName = "Expand" + capitalizeName(field.getName());

            try {
                Files.writeString(Paths.get(directoryPath + "/" + projectionName + ".java"), generateProjectionContent(clazz, field));
            } catch (Exception e) {
                System.out.println("Couldn't generate projection");
            }
        }
    }

    public static String getRelationType(Field relation) {
        String fieldType = relation.getGenericType().toString();
        fieldType = fieldType.replaceFirst("class ", "");

        System.out.println(fieldType);
        if (fieldType.contains("<")) {
            fieldType = fieldType.replaceFirst("\\<.*\\.", "<");
        }

        return fieldType.replaceFirst(".*\\.", "");
    }

    public static String getTypeImports(Field relation) {
        if (relation.getType().isPrimitive()) {
            return "";
        }

        String fieldType = relation.getGenericType().toString();
        fieldType = fieldType.replaceFirst("class ", "");
        String imports = "";

        if (fieldType.contains("<")) {
            String innerImport = fieldType.replaceFirst(".*\\<", "");
            innerImport = innerImport.substring(0, innerImport.length() - 1);

            imports += "import " + innerImport + ";\n";
        }

        String outerImport = fieldType.replaceFirst("\\<.*\\>", "");
        imports += "import " + outerImport + ";\n";

        return imports;
    }

    public static String generateProjectionContent(Class clazz, Field relation) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = clazz.getSimpleName();
        String basePackage = "br.com.uniamerica.gajigo.entity";
        String capitalizedRelation = capitalizeName(relation.getName());

        getRelationType(relation);

        stringBuilder.append("package " + basePackage + ".projection." + name.toLowerCase() + ";\n");

        stringBuilder.append("\n");

        stringBuilder.append("import " + basePackage + "." + name + ";\n");
        stringBuilder.append("import org.springframework.data.rest.core.config.Projection;\n");
        stringBuilder.append(getTypeImports(relation));
        for (Field field : clazz.getDeclaredFields()) {
            if (isRelation(field)) continue;
            stringBuilder.append(getTypeImports(field));
        }


        stringBuilder.append("\n");

        stringBuilder.append("@Projection(name = \"expand" + capitalizedRelation + "\", types = { " + name + ".class })\n");

        stringBuilder.append("public interface Expand" + capitalizedRelation + " {\n");

        stringBuilder.append("    " + getRelationType(relation) + " get" + capitalizedRelation + "();\n");
        for (Field field : clazz.getDeclaredFields()) {
            if (isRelation(field)) continue;
            stringBuilder.append("    " + getRelationType(field) + " get" + capitalizeName(field.getName()) + "();\n");
        }

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
