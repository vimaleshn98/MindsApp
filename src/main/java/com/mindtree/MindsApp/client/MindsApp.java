package com.mindtree.MindsApp.client;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.mindtree.MindsApp.entity.Minds;
import com.mindtree.MindsApp.entity.Tracks;
import com.mindtree.MindsApp.exception.daoexception.TrackDaoException;
import com.mindtree.MindsApp.exception.serviceexception.MidNotFoundService;
import com.mindtree.MindsApp.exception.serviceexception.TrackServiceException;
import com.mindtree.MindsApp.exception.serviceexception.TrackidDuplicateServiceException;
import com.mindtree.MindsApp.exception.serviceexception.TrackidnotfoundService;
import com.mindtree.MindsApp.service.MindsService;
import com.mindtree.MindsApp.service.impl.MindsServiceImpl;

public class MindsApp {
	private static Scanner scanner = new Scanner(System.in);
	private static MindsService mindsService = new MindsServiceImpl();

	public static void main(String[] args) {
		int choice;
		do {
			System.out.println(
					"1 --> Create minds dataCreate minds dataCreate minds data\n 2 --> Delete Details\n 3--> Update data\n 4--> Display All minds\n 5-->Display Specific track\n 6-->add Tracks data\n Enter your choice");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("---------------  Create data----------");
				Tracks mind;
				try {
					mind = mindsService.getDetails();
					boolean flag = mindsService.addTODatabase(mind);
					if (flag) {
						mindsService.UpdateTrack(mind);
					}
				} catch (TrackidDuplicateServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TrackServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("----------- Delete Details\\n -----------------");
				Tracks t;
				try {
					t = mindsService.getTrack();
					boolean flag2 = mindsService.deleteFromDatabase(t);
				} catch (TrackidnotfoundService e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case 3:
				System.out.println("-----------Update data-----------------");
				try {
					Minds m = mindsService.getMid();
					boolean flag = mindsService.upadateFromDatabase(m);
				} catch (MidNotFoundService | TrackidnotfoundService e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 4:
				System.out.println("-----------Display All minds-----------------");
				try {
					List<Tracks> result = mindsService.getAllDetail();
					Iterator<Tracks> i = result.iterator();
					while (i.hasNext()) {
						Tracks f = i.next();
						System.out.println("Track name: " + f.getTrackname() + " Track id is " + f.getTrackid()
								+ " Number of minds in tracks " + f.getNumofMinds());
						System.out.println("\nminds:\n");
						Iterator<Minds> ci = f.getMinds().iterator();
						while (ci.hasNext()) {
							Minds c = ci.next();
							System.out.println(
									c.getId() + " " + c.getName() + " " + c.getPhoneno() + " " + c.getRole() + "\n");
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 5:
				System.out.println("-----------Display Specific track-----------------");
				Tracks result;
				try {
					result = mindsService.getTrackDetail();
					if(result!=null) {
					System.out.println("Track name: " + result.getTrackname() + " Track id is " + result.getTrackid()
							+ " Number of minds in tracks " + result.getNumofMinds());
					System.out.println("\nminds:\n");
					Iterator<Minds> ci = result.getMinds().iterator();
					while (ci.hasNext()) {
						Minds c = ci.next();
						System.out.println(
								c.getId() + " " + c.getName() + " " + c.getPhoneno() + " " + c.getRole() + "\n");
					}
					}else
						System.out.println("Track not found");
				} catch (TrackidnotfoundService e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case 6:
				System.out.println("---------------  Create track data----------");
				Tracks tack = mindsService.getTrackDetails();

				try {
					boolean flag3 = mindsService.addTOTracksDatabase(tack);
				} catch (TrackServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			default:
				System.out.println("-----------------Existing Main Menu-------------------");
			}
		} while (choice <= 6 && choice >= 1);
	}
}
