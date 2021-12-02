package ru.eatit.text_analyse.service.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class HadoopService {

    @PostConstruct
    public void init() throws IOException {
      /*  Configuration conf = new Configuration();
        conf.addResource(new Path("conf/core-site.xml"));
        conf.addResource(new Path("conf/hdfs-site.xml"));
        FileSystem fileSystem = FileSystem.get(conf);
        System.out.println("Подключились к:  " + fileSystem.getUri());
        String dirName = "text";
        Path path = new Path(dirName);
        boolean rez = fileSystem.mkdirs(path);
        fileSystem.close();*/
    }
}
