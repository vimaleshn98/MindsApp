package com.mindtree.MindsApp.dao;

import java.util.List;

import com.mindtree.MindsApp.entity.Minds;
import com.mindtree.MindsApp.entity.Tracks;
import com.mindtree.MindsApp.exception.daoexception.DaoTrackidduplicateException;
import com.mindtree.MindsApp.exception.daoexception.MidNotFoundDao;
import com.mindtree.MindsApp.exception.daoexception.TrackDaoException;
import com.mindtree.MindsApp.exception.daoexception.TrackidNotFound;
import com.mindtree.MindsApp.exception.serviceexception.TrackidDuplicateServiceException;

public interface MindsDao {

	Tracks getDetails() throws TrackDaoException;

	boolean addTODatabase(Tracks mind) throws DaoTrackidduplicateException;

	Tracks getTrackDetails();

	boolean addTOTracksDatabase(Tracks mind) throws TrackDaoException;

	boolean deleteFromDatabase(Tracks t) throws TrackidNotFound;

	Tracks getTrack() throws TrackidNotFound;

	void UpdateTrack(Tracks t);

	Minds getMid() throws MidNotFoundDao ;

	boolean upadateFromDatabase(Minds m) throws TrackidNotFound;

	List<Tracks> getAllDetail() throws Exception;

	Tracks getTrackDetail() throws MidNotFoundDao, TrackidNotFound;

}
