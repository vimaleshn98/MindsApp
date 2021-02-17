package com.mindtree.MindsApp.entity;

import java.util.ArrayList;
import java.util.List;

public class Tracks {
	private short trackid;
	private String trackname;
	private int numofMinds;
	List<Minds> minds = new ArrayList<Minds>();
	private Minds mind;
	public Tracks() {
		// TODO Auto-generated constructor stub
	}
	public Tracks(String trackname) {
		super();
		this.trackname = trackname;
	}
	public Tracks(String track, Minds mind) {
		// TODO Auto-generated constructor stub
		this.trackname = track;
		this.mind= mind;
	}
	public Tracks(short id, String track) {
		// TODO Auto-generated constructor stub
		this.trackname = track;
		this.trackid = id;
		
	}
	public Tracks(short tid, String track, Minds mind2) {
		// TODO Auto-generated constructor stub
		this.trackname = track;
		this.trackid = tid;
		this.mind= mind2;
	}
	public Tracks(short id, String name, int num, List<Minds> m) {
		// TODO Auto-generated constructor stub
		this.trackname = name;
		this.trackid = id;
		this.numofMinds=num;
		this.minds =m;
	}
	public short getTrackid() {
		return trackid;
	}
	public String getTrackname() {
		return trackname;
	}
	public int getNumofMinds() {
		return numofMinds;
	}
	public List<Minds> getMinds() {
		return minds;
	}
	public void setTrackid(short trackid) {
		this.trackid = trackid;
	}
	public void setTrackname(String trackname) {
		this.trackname = trackname;
	}
	public void setNumofMinds(int numofMinds) {
		this.numofMinds = numofMinds;
	}
	public void setMinds(List<Minds> minds) {
		this.minds = minds;
	}
	@Override
	public String toString() {
		return String.format("Tracks [trackid=%s, trackname=%s, numofMinds=%s, minds=%s]", trackid, trackname,
				numofMinds, minds);
	}
	public Minds getMind() {
		return mind;
	}
	public void setMind(Minds mind) {
		this.mind = mind;
	}
	
}
