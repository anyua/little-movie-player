package com.hdfs.dao;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileSystemStatus {
	public List<String> getMovieList() throws IOException{
		List<String> movielist = new ArrayList<String>();
		String path = "/user/hadoop";
		
        String uri = "hdfs:/192.168.133.131:9000/";  
        Configuration config = new Configuration();  
        FileSystem hdfs = FileSystem.get(URI.create(uri), config);  
		Path fpath = new Path(path);
		FileStatus dirStatus = hdfs.getFileStatus(fpath);
		if(dirStatus.isDir())
		{
			System.out.println("这是一个目录");
			for(FileStatus fs:hdfs.listStatus(fpath))
			{
				System.out.println(fs.getPath());
				movielist.add(fs.getPath().toString());
			}
		}
		
		return movielist;
	}
}
