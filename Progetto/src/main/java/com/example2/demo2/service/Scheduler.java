package com.example2.demo2.service;

import java.util.HashSet;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example2.demo2.exception.DownloadException;
import com.example2.demo2.model.CityUV;
import com.example2.demo2.model.Log;
import com.example2.demo2.utils.OpenWeatherDownloader;

/**
 * Componente per schiamare delle operazioni con cadenza fissa durante
 * l'esecuzione dell'applicazione
 * 
 * @author Fabio Mecozzi & Davide De Grazia
 *
 */
@Component
public class Scheduler {

	@Autowired
	CityUVService cityUVService;
	
	@Autowired
	CityService cityService;

	@Autowired
	OpenWeatherDownloader downloader;

	@Autowired
	Log log;

	/**
	 * Optional che, se si verificano eccezioni di tipo DownloadException durante il
	 * download, contiene l'ultima DownloadException
	 */
	Optional<DownloadException> downloadException = Optional.ofNullable(null);

	/**
	 * Metodo che periodicamente si occupa di aggiornare i valori UV nelle CityUV
	 * monitorate della repository gestita da CityUVService
	 */
	@Scheduled(cron = "${scheduler.sampleTime}", zone = "${application.dateTimeZone}")
	public void sampleMonitored() {

		if (downloadException.isPresent()) {
			log.addException(downloadException.get());
		}
		downloadException = Optional.empty();
		this.sample(cityService.getAllMonitored());
	}

	/**
	 * Metodo che, ad intervalli di tempo, si occupa di riscaricare le città che
	 * sampleMonitored non è riuscito a scaricare
	 */
	@Scheduled(fixedRateString = "${scheduler.retryRate}")
	public void handleException() {
		if (downloadException.isPresent()) {
			this.sample(downloadException.get().getCitiesFailed());
		}
	}

	/**
	 * Metodo che chiama il downloader per scaricare una serie di città attraverso
	 * il downloader
	 * 
	 * @param collection
	 */
	private void sample(Iterable<CityUV> collection) {
		try {
			HashSet<CityUV> copy = new HashSet<>();
			for (CityUV cityUV : collection) {
				copy.add(cityUV);
			}
			downloader.download(copy,1);
			cityUVService.update(copy);
			
			downloadException = Optional.ofNullable(null);
		} catch (DownloadException e) {
			downloadException = Optional.of(e);
		}
	}

}
