package com.music.api.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.music.api.constants.URI;
import com.music.api.entity.Artistinfo;
import com.music.api.entity.GoogleResults;
import com.music.api.service.MusicService;

import de.umass.lastfm.Chart;
import de.umass.lastfm.Geo;
import fm.last.musicbrainz.data.dao.ArtistDao;
import fm.last.musicbrainz.data.model.Artist;

@RestController
@RequestMapping(value = URI.ARTISTS)
public class MusicController {

	private MusicService service;

	public MusicController(MusicService service) {
		this.service = service;
	}

	// returns artist info from lastfm api when searched using first,last or full
	// name

	@SuppressWarnings("null")
	@RequestMapping(method = RequestMethod.GET)
	public List<Artist> artists(@PathVariable("id") String id) {
		ArtistDao ad = null;
		return ad.getByName(id);
	}

	// returns albums/tracks related to given artist in search
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	List<Track> tracks(@PathVariable("id") String id) {
		Track trk = null;
		@SuppressWarnings("null")
		TrackData trkData = trk.getTrack();
		return (List<Track>) trkData;
	}
	
	// returns tracks based on geo location i.e by passing country and metro
	@RequestMapping(method = RequestMethod.GET)
	Chart<de.umass.lastfm.Track> geoMetroAlbums(@PathVariable("id") String id) {
		String country = id;
		String metro = id;
		String apiKey = null;
		return Geo.getMetroTrackChart(country , metro, apiKey );
	}

	// returns track lyrics when track_id is passed
	@RequestMapping(method = RequestMethod.GET, value = URI.ID)
	public Lyrics lyrics(@PathVariable("id") String id) {
		Lyrics lyrics = lyrics(id);
		return lyrics;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.ID)
	public String googleImageSearch(@PathVariable("id") String id) throws Exception, UnsupportedEncodingException  {
	    String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String search = "id";
	    String charset = "UTF-8";

	    URL url = new URL(google + URLEncoder.encode(search, charset));
	    Reader reader = new InputStreamReader(url.openStream(), charset);
     	GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
	    return results.getResponseData().getResults().get(0).getUrl();
	}
	

	// returns new Artists info saved through this app
	@RequestMapping(method = RequestMethod.GET)
	public List<Artistinfo> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URI.ID)
	public Artistinfo findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Artistinfo create(@RequestBody Artistinfo Artistinfo) {
		return service.create(Artistinfo);
	}

	@RequestMapping(method = RequestMethod.PUT, value = URI.ID)
	public Artistinfo update(@PathVariable("id") String id, @RequestBody Artistinfo Artistinfo) {
		return service.update(id, Artistinfo);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = URI.ID)
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}