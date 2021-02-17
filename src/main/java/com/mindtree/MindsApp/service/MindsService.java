package com.mindtree.MindsApp.service;

import java.util.List;

import com.mindtree.MindsApp.entity.Minds;
import com.mindtree.MindsApp.entity.Tracks;
import com.mindtree.MindsApp.exception.daoexception.TrackDaoException;
import com.mindtree.MindsApp.exception.serviceexception.MidNotFoundService;
import com.mindtree.MindsApp.exception.serviceexception.TrackServiceException;
import com.mindtree.MindsApp.exception.serviceexception.TrackidDuplicateServiceException;
import com.mindtree.MindsApp.exception.serviceexception.TrackidnotfoundService;

public interface MindsService {

	Tracks getDetails() throws  TrackServiceException;

	boolean addTODatabase(Tracks mind) throws TrackidDuplicateServiceException;

	Tracks getTrackDetails();

	boolean addTOTracksDatabase(Tracks mind) throws TrackServiceException;

	boolean deleteFromDatabase(Tracks t) throws TrackidnotfoundService;

	Tracks getTrack() throws TrackidnotfoundService;

	void UpdateTrack(Tracks t);

	Minds getMid() throws MidNotFoundService;

	boolean upadateFromDatabase(Minds m) throws TrackidnotfoundService;

	List<Tracks> getAllDetail() throws Exception;
	
	Tracks getTrackDetail() throws TrackidnotfoundService;

}
