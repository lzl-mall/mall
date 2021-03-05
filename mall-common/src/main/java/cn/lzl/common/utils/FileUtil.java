package cn.lzl.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("file")
@CrossOrigin
public class FileUtil {
    @RequestMapping("uploadFile")
    public String uploadFile(MultipartFile file){

       /* try {
                Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheetAt = workbook.getSheetAt(1);
            int lastRowNum = sheetAt.getLastRowNum();
            for (int i = 0; i <=lastRowNum; i++) {
                Row row = sheetAt.getRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G8Ta5ZqMhCqpVq14Snb";
        String accessKeySecret = "R6TB531r8FFsmwEQhYyQTanJHFk32f";
        String prefixUrl = "https://1908a.oss-cn-beijing.aliyuncs.com/";


        String originalFilename = file.getOriginalFilename();
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();

            // 创建OSSClient实例。
            OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 创建PutObjectRequest对象。
            //String content = "Hello OSS";
            // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            PutObjectRequest putObjectRequest = new PutObjectRequest("1908a", newFileName, inputStream);

            // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传字符串。
            ossClient.putObject(putObjectRequest);

            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return prefixUrl+newFileName;
    }

    @RequestMapping("uploadExcel")
    public String uploadExcel(MultipartFile file, HttpServletRequest request){
        BufferedInputStream bufferedInputStream = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        String filePathResq = "";
        try {
            String originalFilename = file.getOriginalFilename();
            String newExcelName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
             inputStream = file.getInputStream();
             bufferedInputStream = new BufferedInputStream(inputStream);
            String filePath = "F://excel";
            File file1 = new File(filePath);
            if(!file1.exists()){
                file1.mkdir();
            }
              fileOutputStream = new FileOutputStream(filePath + "/" + newExcelName);
              filePathResq = filePath + "/" + newExcelName;
              bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] buffer = new byte[4096];
            int s= 0;
            while ((s=bufferedInputStream.read(buffer))!= -1){
                bufferedOutputStream.write(buffer,0,s);
                bufferedOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            {
                try {
                    if(bufferedInputStream !=null){
                        bufferedInputStream.close();
                    }
                    if(inputStream !=null){
                        inputStream.close();
                    }
                    if(fileOutputStream !=null){
                        fileOutputStream.close();
                    }
                    if(bufferedOutputStream !=null){
                        bufferedOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return filePathResq;
    }




}
