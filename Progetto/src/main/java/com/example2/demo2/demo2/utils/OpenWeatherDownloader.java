package com.example2.demo2.utils;

import java.util.LinkedHashSet;

import org.springframework.stereotype.Component;

import com.example2.demo2.exception.DownloadException;
import com.example2.demo2.model.CityUV;

@Component
public class OpenWeatherDownloader {

	public void download(Iterable<CityUV> collection) throws DownloadException{
		
	}
	public void download(LinkedHashSet<CityUV> collection) throws DownloadException{
		
	}

}
