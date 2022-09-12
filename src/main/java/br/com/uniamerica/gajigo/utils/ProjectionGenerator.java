package br.com.uniamerica.gajigo.utils;

import org.apache.commons.lang3.ClassUtils;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class ProjectionGenerator {
    private static String packageName = "br.com.uniamerica.gajigo.entity";

    private ProjectionGenerator() {
    }

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        Set<Class> classes = findAllClassesUsingClassLoader(packageName);

        for (Class clazz: classes) {
            if (clazz.isEnum()) {
                continue;
            }

            if (Modifier.isAbstract(clazz.getModifiers())) {
                continue;
            }

            if (!clazz.isAnnotationPresent(Entity.class)) {
                continue;
            }

            generateClassProjections(clazz);
        }
    }

    private static boolean isRelation(Field field) {
        return field.isAnnotationPresent(OneToMany.class)
                || field.isAnnotationPresent(ManyToOne.class)
                || field.isAnnotationPresent(ManyToMany.class)
                || field.isAnnotationPresent(OneToOne.class);
    }

    private static String capitalizeName(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private static void generateClassProjections(Class clazz) {
        String name = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        String directoryPath = "src/main/java/br/com/uniamerica/gajigo/entity/projection/"
                + name.toLowerCase();
        File directory = new File(directoryPath);

        for (Field field : fields) {
            if (!isRelation(field)) {
                continue;
            }

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String projectionName = "Expand" + capitalizeName(field.getName());

            try {
                Files.writeString(
                    Paths.get(directoryPath + "/" + projectionName + ".java"),
                    generateProjectionContent(clazz, field)
                );
            } catch (Exception e) {
                System.out.println("Couldn't generate projection");
            }
        }
    }

    private static String getRelationType(Field relation) {
        String fieldType = relation.getGenericType().toString();
        fieldType = fieldType.replaceFirst("class ", "");

        System.out.println(fieldType);
        if (fieldType.contains("<")) {
            fieldType = fieldType.replaceFirst("\\<.*\\.", "<");
        }

        return fieldType.replaceFirst(".*\\.", "");
    }

    private static String getTypeImports(Field relation) {
        Class<?> type = relation.getType();
        if (ClassUtils.isPrimitiveOrWrapper(type)
            || type == String.class) {
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

    private static String generateProjectionContent(Class clazz, Field relation) {
        StringBuilder stringBuilder = new StringBuilder();
        String name = clazz.getSimpleName();
        String capitalizedRelation = capitalizeName(relation.getName());

        getRelationType(relation);

        stringBuilder.append("package " + packageName + ".projection." + name.toLowerCase() + ";\n");

        stringBuilder.append("\n");

        stringBuilder.append("import " + packageName + "." + name + ";\n");
        stringBuilder.append("import org.springframework.data.rest.core.config.Projection;\n");

        String relationImport = getTypeImports(relation);
        if (!relationImport.isEmpty()) {
            stringBuilder.append(getTypeImports(relation));
        }

        Set<String> imports = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (isRelation(field)) {
                continue;
            }

            String customImport = getTypeImports(field);

            if (customImport.isEmpty()) {
                continue;
            }

            if (imports.contains(customImport)) {
                continue;
            }

            imports.add(customImport);
            stringBuilder.append(customImport);
        }

        stringBuilder.append("\n");

        stringBuilder.append(
            "@Projection(name = \"expand" + capitalizedRelation + "\", types = { " + name + ".class })\n"
        );

        stringBuilder.append("public interface Expand" + capitalizedRelation + " {\n");

        stringBuilder.append("    " + getRelationType(relation) + " get" + capitalizedRelation + "();\n");
        for (Field field : clazz.getDeclaredFields()) {
            if (isRelation(field)) {
                continue;
            }

            stringBuilder.append("    " + getRelationType(field) + " get" + capitalizeName(field.getName()) + "();\n");
        }

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
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
