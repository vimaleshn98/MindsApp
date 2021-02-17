package com.mindtree.MindsApp.service.impl;

import java.util.List;
import java.util.Scanner;

import javax.sound.midi.Track;

import com.mindtree.MindsApp.dao.MindsDao;
import com.mindtree.MindsApp.dao.impl.MindsDaoImpl;
import com.mindtree.MindsApp.entity.Minds;
import com.mindtree.MindsApp.entity.Tracks;
import com.mindtree.MindsApp.exception.daoexception.DaoTrackidduplicateException;
import com.mindtree.MindsApp.exception.daoexception.MidNotFoundDao;
import com.mindtree.MindsApp.exception.daoexception.TrackDaoException;
import com.mindtree.MindsApp.exception.daoexception.TrackidNotFound;
import com.mindtree.MindsApp.exception.serviceexception.MidNotFoundService;
import com.mindtree.MindsApp.exception.serviceexception.TrackServiceException;
import com.mindtree.MindsApp.exception.serviceexception.TrackidDuplicateServiceException;
import com.mindtree.MindsApp.exception.serviceexception.TrackidnotfoundService;
import com.mindtree.MindsApp.service.MindsService;

public class MindsServiceImpl implements MindsService{
	MindsDao dao = new MindsDaoImpl();
	@Override
	public Tracks getDetails() throws TrackServiceException  {
		// TODO Auto-generated method stub
		Tracks track;
		try {
			track = dao.getDetails();
		} catch (TrackDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackServiceException("Track name not found in DB");
		}
		return track;
	}
	@Override
	public boolean addTODatabase(Tracks mind) throws TrackidDuplicateServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.addTODatabase(mind);
		} catch (DaoTrackidduplicateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidDuplicateServiceException("track id is already in use");
		}
	}
	@Override
	public Tracks getTrackDetails() {
		// TODO Auto-generated method stub
		Tracks track = dao.getTrackDetails();
		return track;
	}
	@Override
	public boolean addTOTracksDatabase(Tracks mind) throws TrackServiceException  {
		// TODO Auto-generated method stub
		try {
			return dao.addTOTracksDatabase(mind);
		} catch (TrackDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackServiceException("insertion failed");
		}
	}
	@Override
	public boolean deleteFromDatabase(Tracks t) throws TrackidnotfoundService {
		// TODO Auto-generated method stub
		
		try {
			return dao.deleteFromDatabase(t);
		} catch (TrackidNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidnotfoundService("id not found");
		}
	}
	@Override
	public Tracks getTrack() throws TrackidnotfoundService {
		// TODO Auto-generated method stub
		try {
			return dao.getTrack();
		} catch (TrackidNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidnotfoundService("track not found");
		}
	}
	@Override
	public void UpdateTrack(Tracks t) {
		// TODO Auto-generated method stub
		dao. UpdateTrack(t);
	}
	@Override
	public Minds getMid() throws MidNotFoundService  {
		// TODO Auto-generated method stub
		try {
			return dao.getMid();
		} catch (MidNotFoundDao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MidNotFoundService("minds not found");
		}
	}
	@Override
	public boolean upadateFromDatabase(Minds m) throws TrackidnotfoundService {
		// TODO Auto-generated method stub
		try {
			return dao.upadateFromDatabase(m);
		} catch (TrackidNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidnotfoundService("track not found");
		}
	}
	@Override
	public List<Tracks> getAllDetail() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllDetail();
	}
	@Override
	public Tracks getTrackDetail() throws TrackidnotfoundService {
		// TODO Auto-generated method stub
		try {
			return dao.getTrackDetail();
		} catch (MidNotFoundDao | TrackidNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidnotfoundService("Track not found");
		}
	}
	
}
