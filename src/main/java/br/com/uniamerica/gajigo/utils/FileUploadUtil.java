package br.com.uniamerica.gajigo.utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


import liquibase.util.file.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public final class FileUploadUtil {
    private FileUploadUtil() {
    }

    public static String saveFile(String fileName, MultipartFile multipartFile, Long id)
            throws IOException, NoSuchAlgorithmException {
        Path uploadPath = Paths.get("uploads");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = generateFileName(id);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            String nameFile = fileCode + "-" + fileName;
            System.out.println("\n\n\n\nnameFile: " + nameFile);
            validType(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return fileCode;
    }

    public static void validType(String falename) {
        String extention = FilenameUtils.getExtension(falename);
        System.out.println("extention: " + extention);
        if (extention.equals("jpg") || extention.equals("png") || extention.equals("jpeg")) {
            System.out.println("formato compativel");
        } else {
            throw new RuntimeException("formato incompativel");
        }
    }
    public static String generateFileName(Long id) throws RuntimeException, NoSuchAlgorithmException {
        String fileName = (id.toString() + LocalDateTime.now());
        String hashFilename;

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] digest = messageDigest.digest(fileName.getBytes());
        hashFilename = (new HexBinaryAdapter()).marshal(digest);


        return hashFilename;
    }
}
