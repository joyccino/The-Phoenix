package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
  private Connection conn;
  private ResultSet rs;

  public BbsDAO() {
  	try {
  		String dbURL = "jdbc:sqlserver://DESKTOP-S2IGL5G\\SQLEXPRESS:1433;" + "databaseName=BBS;";
  		String dbID = "sa";
  		String dbPassword = "P@ssw0rd1!";
  		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
  		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
  }

  public String getDate() {
  	String SQL = "SELECT GETDATE()"; // 현재 시간 가져오기
  	try {
  		PreparedStatement pstmt = conn.prepareStatement(SQL);
  		rs = pstmt.executeQuery();
  		if (rs.next()) {
  			return rs.getString(1);
  		}
  	} catch(Exception e) {
  		e.printStackTrace();
  	}
  	return "";
  }

  public int getNext() { // 다음 글 가지고 오기.
  	String SQL = "SELECT bbsID FROM [BBS] ORDER BY bbsID DESC";
  	try {
  		PreparedStatement pstmt = conn.prepareStatement(SQL);
  		rs = pstmt.executeQuery();
  		if (rs.next()) {
  			return rs.getInt(1) + 1;
  		}
  		return 1; // 첫 번째 게시물인 경우
  	} catch(Exception e) {
  		e.printStackTrace();
  		return -1; // 데이터베이스 오류
  	}
  	
  }

  public int write(String bbsTitle, String userID, String bbsContent) {
  	String SQL = "INSERT INTO [BBS] VALUES (?, ?, ?, ?, ?);";
  	try {
  		PreparedStatement pstmt = conn.prepareStatement(SQL);
  		//pstmt.setInt(1, getNext());
  		pstmt.setString(1, bbsTitle);
  		pstmt.setString(2, userID);
  		pstmt.setString(3, getDate());
  		pstmt.setString(4, bbsContent);
  		pstmt.setInt(5, 1);
  		return pstmt.executeUpdate();
  	} catch(Exception e) {
  		e.printStackTrace();
  		return -1; // 데이터베이스 오류
  	}
  	
  }
  
  public ArrayList<Bbs> getList(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID;";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(1));
				list.add(bbs);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 10);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Bbs getBbs(int bbsID) {
		String SQL = "SELECT * FROM [BBS] WHERE bbsID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(1));
				return bbs;
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String SQL = "UPDATE [BBS] SET bbsTitle = ?, bbsContent = ? WHERE bbsID =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);

			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int delete(int bbsID) {
		String SQL = "UPDATE [BBS] SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);

			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}