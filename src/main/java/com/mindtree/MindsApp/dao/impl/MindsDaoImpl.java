package com.mindtree.MindsApp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.MindsApp.dao.MindsDao;
import com.mindtree.MindsApp.entity.Minds;
import com.mindtree.MindsApp.entity.Tracks;
import com.mindtree.MindsApp.exception.daoexception.DaoTrackidduplicateException;
import com.mindtree.MindsApp.exception.daoexception.MidNotFoundDao;
import com.mindtree.MindsApp.exception.daoexception.TrackDaoException;
import com.mindtree.MindsApp.exception.daoexception.TrackidNotFound;
import com.mindtree.MindsApp.exception.serviceexception.TrackidDuplicateServiceException;
import com.mindtree.MindsApp.utility.Database;


public class MindsDaoImpl implements MindsDao {
	Scanner scanner = new Scanner(System.in);
	Database db = new Database();

	@Override
	public Tracks getDetails() throws TrackDaoException {
		System.out.println("Enter the id of Mind");
		short id = scanner.nextShort();
		System.out.println("Enter the name of Mind");
		scanner.nextLine();
		String name = scanner.nextLine();
		System.out.println("Enter the contact no. of Mind");
		String phone = scanner.nextLine();
		System.out.println("Enter the role of Mind");
		String role = scanner.nextLine();
		System.out.println("Enter the Track Name");
		String track = scanner.nextLine();
		short tid =getIdForTrack(track);
		System.out.println(tid);
		Minds mind = new Minds(id, name, phone, role);
		Tracks tracks = new Tracks(tid,track, mind);
		return tracks;
	}

	private short getIdForTrack(String track) throws TrackidNotFound {
		// TODO Auto-generated method stub
		Connection conn  =null;
		Statement st =null;
		ResultSet rs = null;
		try {
			conn =db.getConnection();
			st =conn.createStatement();
			rs = st.executeQuery("select id from Tracks where name = '"+track+"'");
			if(rs==null) {
				return -1;
			}
			rs.next();
			return rs.getShort(1);
			
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println();
			throw new TrackidNotFound("Track id is not found");
		}
		
		finally {
			db.closeConnection(conn);
			db.closeStatement(st);
		}
	}

	@Override
	public boolean addTODatabase(Tracks mind) throws DaoTrackidduplicateException {
		// TODO Auto-generated method stub
		int i=0,j = 0;
		Connection con =null;
		PreparedStatement preparedStatement = null;
		try {
//			 con= db.getConnection();
			con = db.getConnection();
			Minds m = mind.getMind();
			String sql2 = "insert into Minds values(?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql2);
			preparedStatement.setShort(1, m.getId());
			preparedStatement.setString(2, m.getName());
			preparedStatement.setString(3, m.getPhoneno());
			preparedStatement.setString(4, m.getRole());
			System.out.println(mind.getTrackid());
			preparedStatement.setShort(5, mind.getTrackid());
			j = preparedStatement.executeUpdate();
			if(j>0) {
				return true;
			}else
				return false;
		}catch (SQLIntegrityConstraintViolationException e ) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println();
			throw new DaoTrackidduplicateException("Track id already assigned");
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
			System.out.println();
			throw new DaoTrackidduplicateException("Track id already assigned");
		}
		finally {
			db.closeConnection(con);
			db.closePrepareStatement(preparedStatement);
		}
	}

	@Override
	public Tracks getTrackDetails() {
		// TODO Auto-generated method stub
		System.out.println("Enter the Track id");
		short id = scanner.nextShort();
		scanner.nextLine();
		System.out.println("Enter the Track Name");
		String trackname = scanner.nextLine();
		System.out.println(trackname);
		Tracks tracks = new Tracks(id,trackname);
		return tracks;
	}

	@Override
	public boolean addTOTracksDatabase(Tracks mind) throws TrackDaoException {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement preparedStatement = null;
		try {
//		con = db.getConnection();
		con = db.getConnection();
//		int  num=getTrackNumberOfMinds(mind);
		String sql = "insert into Tracks values(?,?,?)";
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setShort(1, mind.getTrackid());
		preparedStatement.setString(2, mind.getTrackname());
		preparedStatement.setInt(3, 0);
		int i = preparedStatement.executeUpdate();
		if(i>0) {
			return true;
		}else
			return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackDaoException("id is alreay used");
		}
		finally {
			db.closeConnection(con);
			db.closePrepareStatement(preparedStatement);
		}
	}

	private int getTrackNumberOfMinds(Tracks mind) throws Exception {
		// TODO Auto-generated method stub
		Connection conn  =null;
		Statement st =null;
		ResultSet rs = null;
		try {
			conn =db.getConnection();
			st =conn.createStatement();
			rs = st.executeQuery("select numofminds from Tracks where id = "+mind.getTrackid()+"");
			if(rs==null) {
				return -1;
			}else {
				rs.next();
				return rs.getInt(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Track is not found");
			throw e;
//			e.printStackTrace();
		}
		finally {
			db.closeConnection(conn);
			db.closeStatement(st);
		}
	}

	@Override
	public boolean deleteFromDatabase(Tracks t) throws TrackidNotFound {
		// TODO Auto-generated method stub
		int temp=0;
		Connection con =null;
		Statement st=null;
		try {
			con = db.getConnection();
			con = db.getConnection();
			String sql = "delete from Tracks WHERE id = "+ t.getTrackid()+"";
			System.out.println(sql);
			st = con.createStatement();
			temp= st.executeUpdate(sql);
			if(temp>0)
				return true;
			else
				return false;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidNotFound("id not found");
		} 
		finally {
			db.closeConnection(con);
			db.closeStatement(st);
		}
		
	}

	@Override
	public Tracks getTrack() throws TrackidNotFound   {
		// TODO Auto-generated method stub
//		scanner.nextLine();
		System.out.println("Enter the Track Name");
		String track = scanner.nextLine();
		short tid =getIdForTrack(track);
		System.out.println(tid);
		Tracks tracks = new Tracks(tid,track);
		return tracks;
	}

	@Override
	public void UpdateTrack(Tracks t) {
		// TODO Auto-generated method stub
		Connection con;
		try {
//			con = db.getConnection();
			con = db.getConnection();
			int  num=(getTrackNumberOfMinds(t)+1);
			String sql = "UPDATE Tracks SET numofminds = "+ num +" WHERE id = "+ t.getTrackid()+"";
			System.out.println(sql);
			Statement st = con.createStatement();
			int temp= st.executeUpdate(sql);
			System.out.println(temp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Minds getMid() throws MidNotFoundDao {
		// TODO Auto-generated method stub
		System.out.println("enter the MId ");
		short mid = scanner.nextShort();
		Connection conn  =null;
		Statement st =null;
		ResultSet rs = null;
		try {
			conn =db.getConnection();
			st =conn.createStatement();
			rs = st.executeQuery("select * from Minds where id = "+mid+"");
			if(rs==null) {
				return null;
			}else {
				rs.next();
				return new Minds(rs.getShort(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("");
			e.printStackTrace();
			throw new MidNotFoundDao("Mind is not found");
		}
	}

	@Override
	public boolean upadateFromDatabase(Minds m) throws TrackidNotFound {
		// TODO Auto-generated method stub
		int temp=0;
		Connection con=null;
		Statement st = null;
		try {
			con = db.getConnection();
			con = db.getConnection();
			System.out.println("enter the new phone number");
			String newphoneno =scanner.next();
			String sql = "UPDATE Minds SET phone = "+ newphoneno +" WHERE id = "+ m.getId()+"";
			System.out.println(sql);
			st = con.createStatement();
			temp= st.executeUpdate(sql);
			System.out.println(temp);
			if(temp>0)
				return true;
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TrackidNotFound("id not found");
		}
		finally {
			db.closeConnection(con);
			db.closeStatement(st);
		}
	}

	@Override
	public List<Tracks> getAllDetail() throws Exception {
		// TODO Auto-generated method stub
		Connection conn  =null;
		Statement st =null;
		ResultSet rs = null;
		List<Tracks> temp = new ArrayList<Tracks>();
		try {
			conn =db.getConnection();
			st =conn.createStatement();
			rs = st.executeQuery("select * from Tracks");
			while(rs.next()) {
				List<Minds> m =getByTrack(rs.getInt("id"));
				Tracks t = new Tracks(rs.getShort("id"),rs.getString("name"),rs.getInt("numofminds"),m);
				temp.add(t);
			}
			return temp;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Track is not found");
			throw e;
//			e.printStackTrace();
		}
		finally {
			db.closeConnection(conn);
			db.closeStatement(st);
			db.closeResultset(rs);
		}
	}

	private List<Minds> getByTrack(int tid) throws MidNotFoundDao {
		Connection conn  =null;
		Statement st =null;
		ResultSet rs = null;
		List<Minds> temp = new ArrayList<Minds>();
		try {
			conn =db.getConnection();
			st =conn.createStatement();
			rs = st.executeQuery("select * from Minds where trackid="+tid+"");
			while(rs.next()) {
				Minds t = new Minds(rs.getShort("id"),rs.getString("name"),rs.getString("phone"),rs.getString("role"));
				temp.add(t);
			}
			return temp;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MidNotFoundDao("Minds not found");
//			
		}
		finally {
			db.closeConnection(conn);
			db.closeStatement(st);
			db.closeResultset(rs);
		}
	}

	@Override
	public Tracks getTrackDetail() throws MidNotFoundDao,TrackidNotFound {
		// TODO Auto-generated method stub
		Connection conn  =null;
		Statement st =null;
		ResultSet rs = null;
		Tracks t =null;
		System.out.println("enter the track name");
		String track = scanner.nextLine();
		try {
			conn =db.getConnection();
			st =conn.createStatement();
			rs = st.executeQuery("select * from Tracks where name='"+track+"'");
			while(rs.next()) {
				List<Minds> m =getByTrack(rs.getInt("id"));
				t = new Tracks(rs.getShort("id"),rs.getString("name"),rs.getInt("numofminds"),m);
			}
			return t;
			
		} catch (ClassNotFoundException | SQLException |NullPointerException e) {
			e.printStackTrace();
			throw new TrackidNotFound("Track is not found");
//			
		}
		finally {
			db.closeConnection(conn);
			db.closeStatement(st);
			db.closeResultset(rs);
		}
	}

}
