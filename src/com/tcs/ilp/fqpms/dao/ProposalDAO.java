package com.tcs.ilp.fqpms.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tcs.ilp.fqpms.exception.FQException;
import com.tcs.ilp.fqpms.model.BodyInjuryProposal;
import com.tcs.ilp.fqpms.model.Insurance;
import com.tcs.ilp.fqpms.model.LiabilityProposal;
import com.tcs.ilp.fqpms.model.Proposal;
import com.tcs.ilp.fqpms.model.SearchQuery;
import com.tcs.ilp.fqpms.model.Vehicle;
import com.tcs.ilp.fqpms.model.VehicleDamageProposal;
import com.tcs.ilp.fqpms.model.User;


public class ProposalDAO {
	
	/**
	 * View insurance and vehicle details
	 * @param uid
	 * @return
	 * @throws FQException
	 */
	public Insurance getInsuranceDetails(String uid, String vid, String type) throws FQException {
		Connection con = null;;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Insurance temp = null;

		try{
			con = DatabaseUtil.getConnection();
			con.setAutoCommit(false);
		if (type.contains("bodyinjury")) {
			ps = con.prepareStatement("select * from GROUPB_FQ_Insurance where user_id = ? and Type_Of_Insurance = ?");
			ps.setString(1, uid);
			ps.setString(2, "Body Injury Coverage");
		} else if (type.contains("vehicledamage")) {
			ps = con.prepareStatement("select * from GROUPB_FQ_Insurance where user_id = ? and Type_Of_Insurance = ? and vehicle_id = ?");
			ps.setString(1, uid);
			ps.setString(2, "Vehicle Damage Coverage");
			ps.setString(3, vid);
		} else if (type.contains("liability")) {
			ps = con.prepareStatement("select * from GROUPB_FQ_Insurance where user_id = ? and Type_Of_Insurance = ? and vehicle_id = ?");
			ps.setString(1, uid);
			ps.setString(2, "Liability Coverage");
			ps.setString(3, vid);
		}
		rs = ps.executeQuery();
		while (rs.next()) {
			temp = new Insurance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getDate(6), rs.getDouble(7), rs.getString(8), rs.getDouble(9),rs.getDouble(10));
			}
		   con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new FQException(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return temp;
	}
	
	public Vehicle getVehicleDetails(String vid) throws FQException {
		Connection con = null;;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vehicle temp = null;
		
		try{
	    con = DatabaseUtil.getConnection();
		ps = con.prepareStatement("select * from GROUPB_FQ_Vehicle where vehicle_id = ? ");
		ps.setString(1, vid);
		rs = ps.executeQuery();
		while (rs.next()) {
			temp = new Vehicle(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
			temp.setLincesenumber(rs.getString(8));
			}
		    
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return temp;
	}
	
	
	
	
	/**
	 * Original FQPMS-V6
	 * @param uid
	 * @return
	 * @throws FQException
	 */

	
	public ArrayList<Proposal> getAllProposalsByUserID(String uid)
			throws FQException {
		Connection con = null;;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proposal pro = null;
		ArrayList<Proposal> result = null;
		try {
			con =  DatabaseUtil.getConnection();
			con.setAutoCommit(false);
			result = new ArrayList<Proposal>();
			ps = con
					.prepareStatement("select * from GROUPB_FQ_BodyInjuryProposal where user_id = ? ");
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while (rs.next()) {
				pro = new BodyInjuryProposal(rs.getString(1), rs.getString(2),
						rs.getDate(3), rs.getDouble(4), rs.getString(6), rs
								.getString(5), rs.getString(7));
				pro.setInsuranceid(rs.getString(8));
				result.add(pro);
			}
			ps = con
					.prepareStatement("select * from GROUPB_FQ_VehicleProposal where user_id = ?");
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while (rs.next()) {
				pro = new VehicleDamageProposal(rs.getString(1), rs
						.getString(2), rs.getString(3), rs.getDate(4), rs
						.getDouble(5), rs.getString(6), rs.getString(7));
				pro.setInsuranceid(rs.getString(8));
				result.add(pro);
			}
			ps = con
					.prepareStatement("select * from GROUPB_FQ_LiabilityProposal where user_id = ?");
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while (rs.next()) {
				pro = new LiabilityProposal(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDate(4), rs.getDouble(5), rs
								.getString(6), rs.getString(7));
				pro.setInsuranceid(rs.getString(8));
				result.add(pro);
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new FQException(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return result;
	}

	public boolean addProposal(BodyInjuryProposal proposal) throws FQException {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("insert into GROUPB_FQ_BodyInjuryProposal values(?,?,?,?,?,?,?,?)");
			ps.setString(1, proposal.getProposalid());
			ps.setString(2, proposal.getUserid());
			ps.setDate(3, new java.sql.Date(proposal.getDateofoccurance()
					.getTime()));
			ps.setDouble(4, proposal.getAmountclaimed());
			ps.setString(5, proposal.getHospitalname());
			ps.setString(6, proposal.getNatureofinjury());
			ps.setString(7, "");
			ps.setString(8, proposal.getInsuranceid());
			if (ps.executeUpdate() > 0) {
				result = true;
			}
			ps=con.prepareStatement("update GROUPB_FQ_Insurance set");
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return result;
	}

	public boolean addProposal(VehicleDamageProposal proposal)
			throws FQException {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("insert into GROUPB_FQ_VehicleProposal values(?,?,?,?,?,?,?,?)");
			ps.setString(1, proposal.getProposalid());
			ps.setString(2, proposal.getUserid());
			ps.setString(3, proposal.getVehicleid());
			ps.setDate(4, new java.sql.Date(proposal.getDateofoccurance()
					.getTime()));
			ps.setDouble(5, proposal.getAmountclaimed());
			ps.setString(6, proposal.getNatureofdamage());
			ps.setString(7, "");
			ps.setString(8, proposal.getInsuranceid());
			if (ps.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return result;
	}

	public boolean addProposal(LiabilityProposal proposal) throws FQException {
		boolean result = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("insert into GROUPB_FQ_LiabilityProposal values(?,?,?,?,?,?,?,?)");
			ps.setString(1, proposal.getProposalid());
			ps.setString(2, proposal.getUserid());
			ps.setString(3, proposal.getVehicleid());
			ps.setDate(4, new java.sql.Date(proposal.getDateofoccurance()
					.getTime()));
			ps.setDouble(5, proposal.getAmountclaimed());
			ps.setString(6, proposal.getTypeofliability());
			ps.setString(7, "");
			ps.setString(8, proposal.getInsuranceid());
			if (ps.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return result;
	}

	public boolean validateDuationCoverage(String insuranceId,
			java.util.Date dateOfOccurance, File xmlFile) throws FQException {
		Boolean flag = false;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			Node nNode = null;
			Element eElement = null;
			Calendar calendar = null;
			java.util.Date date2 = null;
			NodeList nList = doc.getElementsByTagName("fquserdetails");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					eElement = (Element) nNode;
					String st1 = eElement.getElementsByTagName("fqinsuranceid")
							.item(0).getTextContent();
					String st2 = st1.substring(0, st1.length() - 1);
					if (st2.equals(insuranceId)) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd-MMM-yyyy");
						String st3 = eElement.getElementsByTagName(
								"dateofpayment").item(0).getTextContent();
						String st4 = st3.substring(0, st3.length() - 1);
						java.util.Date date1 = sdf.parse(st4);
						double payment = getPremiumById(insuranceId);
						int duration = (int) (Double.parseDouble(eElement
								.getElementsByTagName("amountpaid").item(0)
								.getTextContent()) / payment);
						calendar = Calendar.getInstance();
						calendar.setTime(date1);
						int month = calendar.get(Calendar.MONTH) + duration;
						calendar.set(Calendar.MONTH, month);
						date2 = calendar.getTime();
						if (dateOfOccurance.compareTo(date1) >= 0
								&& dateOfOccurance.compareTo(date2) <= 0) {
							flag = true;
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			throw new FQException(e.getMessage());
		}
		return flag;
	}

	public boolean validateAmount(String insuranceId, double amount)
			throws FQException {
		System.out.println(insuranceId);
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("select coverage_amount from GROUPB_FQ_Insurance where insurance_Id = ?");
			double insuranceAmt = 0;
			ps.setString(1, insuranceId);
			System.out.println(insuranceId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				insuranceAmt = rs.getDouble(1);
			}
	
			if (amount <= insuranceAmt) {
				flag = true;
			}
			System.out.println(flag);
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return flag;
	}

	public int getNextID() throws FQException {
		int next = 0, n1, n2, n3;
		String s1, s2, s3;
		StringBuffer sb = new StringBuffer();
		String maxID_b = getMaxID("GROUPB_FQ_BodyInjuryProposal");
		if(maxID_b==null)
			maxID_b = "propi0";
		sb.append(maxID_b);
		s1 = sb.substring(5).toString();
		n1 = Integer.parseInt(s1);
		sb = new StringBuffer();
		String maxID_v = getMaxID("GROUPB_FQ_VehicleProposal");
		if(maxID_v == null)
			maxID_v = "propv1";
		sb.append(maxID_v);
		s2 = sb.substring(5).toString();
		n2 = Integer.parseInt(s2);
		sb = new StringBuffer();
		String maxID_l = getMaxID("GROUPB_FQ_LiabilityProposal");
		if(maxID_l ==null)
			maxID_l = "propl2";
		sb.append(maxID_l);
		s3 = sb.substring(5).toString();
		n3 = Integer.parseInt(s3);
		if (n1 > next)
			next = n1;
		if (n2 > next)
			next = n2;
		if (n3 > next)
			next = n3;
		return next;
	}

	private String getMaxID(String type) throws FQException {
		Connection con = null;
		String pid = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement("select * from " + type
					+ " order by substr(prop_id, 6, length(prop_id)-5)*1 desc");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				pid = rs.getString(1);
			
		} catch (SQLException e) {
			throw new FQException(e.getMessage());	
		}
		finally{
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return pid;
	}

	public String getInsuranceID(String uid, String vid, String type)
			throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		String result = null;
		try {
			con = DatabaseUtil.getConnection();
			con.setAutoCommit(false);
			if (type.contains("bodyinjury")) {
				ps = con
						.prepareStatement("select Insurance_ID from GROUPB_FQ_Insurance where user_id = ? and Type_Of_Insurance = ?");
				ps.setString(1, uid);
				ps.setString(2, "Body Injury Coverage");
			} else if (type.contains("vehicledamage")) {
				ps = con
						.prepareStatement("select Insurance_ID from GROUPB_FQ_Insurance where user_id = ? and Type_Of_Insurance = ? and vehicle_id = ?");
				ps.setString(1, uid);
				ps.setString(2, "Vehicle Damage Coverage");
				ps.setString(3, vid);
			} else if (type.contains("liability")) {
				ps = con
						.prepareStatement("select Insurance_ID from GROUPB_FQ_Insurance where user_id = ? and Type_Of_Insurance = ? and vehicle_id = ?");
				ps.setString(1, uid);
				ps.setString(2, "Liability Coverage");
				ps.setString(3, vid);
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				result = rs.getString(1);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new FQException(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return result;
	}

	private Double getPremiumById(String insuranceId) throws FQException {
		Connection con = null;
		Double result = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("select Monthly_Premium from GROUPB_FQ_Insurance where Insurance_ID = ?");
			ps.setString(1, insuranceId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				result = rs.getDouble(1);
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return result;
	}

	public Proposal getProposalById(String proposalid) throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proposal pro = null;
		try {
			con = DatabaseUtil.getConnection();
			con.setAutoCommit(false);
			if (proposalid.startsWith("propi"))
				ps = con
						.prepareStatement("select * from GROUPB_FQ_BodyInjuryProposal where prop_id=?");
			else if (proposalid.startsWith("propv"))
				ps = con
						.prepareStatement("select * from GROUPB_FQ_VehicleProposal where prop_id=?");
			else if (proposalid.startsWith("propl"))
				ps = con
						.prepareStatement("select * from GROUPB_FQ_LiabilityProposal where prop_id=?");
			ps.setString(1, proposalid);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (proposalid.startsWith("propi")) {
					pro = new BodyInjuryProposal(proposalid, rs.getString(2),
							rs.getDate(3), rs.getDouble(4), rs.getString(6), rs
									.getString(5), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
				} else if (proposalid.startsWith("propv")) {
					pro = new VehicleDamageProposal(proposalid,
							rs.getString(2), rs.getString(3), rs.getDate(4), rs
									.getDouble(5), rs.getString(6), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
				} else if (proposalid.startsWith("propl"))
					pro = new LiabilityProposal(proposalid, rs.getString(2), rs
							.getString(3), rs.getDate(4), rs.getDouble(5), rs
							.getString(6), rs
							.getString(7));
				pro.setInsuranceid(rs.getString(8));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		return pro;
	}

	public boolean updateProposal(BodyInjuryProposal proposal)
			throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		if(proposal.getQuoteId()!=""&&proposal.getQuoteId()!=null){
			throw new FQException("Quote has been generated for this proposal");
		}
		else{
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("update GROUPB_FQ_BodyInjuryProposal set date_of_occurance=?, amount_claimed=?, hospital_name=?, nature_of_injury=?, insurance_id = ? where prop_id=?");
			ps.setDate(1, new Date(proposal.getDateofoccurance().getTime()));
			ps.setDouble(2, proposal.getAmountclaimed());
			ps.setString(3, proposal.getHospitalname());
			ps.setString(4, proposal.getNatureofinjury());
			ps.setString(6, proposal.getProposalid());
			ps.setString(5, proposal.getInsuranceid());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		}
		return flag;
	}

	public boolean updateProposal(VehicleDamageProposal proposal)
			throws FQException {

		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		if(proposal.getQuoteId()!=""&&proposal.getQuoteId()!=null){
			throw new FQException("Quote has been generated for this proposal");
		}
		else{
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("update GROUPB_FQ_VehicleProposal set vehicle_id=?, "
							+ "date_of_occurance=?, amount_claimed=?, nature_of_damage=? , insurance_id = ?"
							+ "where prop_id=?");
			ps.setString(1, proposal.getVehicleid());
			ps.setDate(2, new Date(proposal.getDateofoccurance().getTime()));
			ps.setDouble(3, proposal.getAmountclaimed());
			ps.setString(4, proposal.getNatureofdamage());
			ps.setString(6, proposal.getProposalid());
			ps.setString(5, proposal.getInsuranceid());
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		}
		return flag;
	}

	public boolean updateProposal(LiabilityProposal proposal)
			throws FQException {
		Connection con = null;
		boolean flag = false;
		PreparedStatement ps = null;
		
		if(proposal.getQuoteId()!=""&&proposal.getQuoteId()!=null){
			throw new FQException("Quote has been generated for this proposal");
		}
		else{
		
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("update GROUPB_FQ_LiabilityProposal set vehicle_id=?, date_of_occurance=?, amount_claimed=?, type_of_liability=?, insurance_id = ? where prop_id=?");
			ps.setString(1, proposal.getVehicleid());
			ps.setDate(2, new Date(proposal.getDateofoccurance().getTime()));
			ps.setDouble(3, proposal.getAmountclaimed());
			ps.setString(4, proposal.getTypeofliability());
			ps.setString(5, proposal.getInsuranceid());
			ps.setString(6, proposal.getProposalid());
			
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		}
		return flag;
	}

	public boolean deleteProposalByID(String pid) throws FQException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
			con = DatabaseUtil.getConnection();
			con.setAutoCommit(false);
			Proposal proposal = getProposalById(pid);
			
			if(proposal.getQuoteId()!=""&&proposal.getQuoteId()!=null){
				throw new FQException("Quote has been generated for this proposal");
			}
			
			else if (proposal.getProposalid().startsWith("propi")) {
				ps = con
						.prepareStatement("delete from GROUPB_FQ_BodyInjuryProposal where prop_ID=?");
				ps.setString(1, proposal.getProposalid());
			} else if (proposal.getProposalid().startsWith("propv")) {
				ps = con
						.prepareStatement("delete from GROUPB_FQ_VehicleProposal where prop_ID=?");
				ps.setString(1, proposal.getProposalid());
			} else if (proposal.getProposalid().startsWith("propl")) {
				ps = con
						.prepareStatement("delete from GROUPB_FQ_LiabilityProposal where prop_ID=?");
				ps.setString(1, proposal.getProposalid());
			}
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new FQException(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		
		return flag;
	}

	public Proposal getUserProposalById(String userid, String proposalid)
			throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proposal pro = null;
		try {
			con = DatabaseUtil.getConnection();
			con.setAutoCommit(false);
			if (proposalid.startsWith("propi"))
				ps = con
						.prepareStatement("select * from GROUPB_FQ_BodyInjuryProposal where prop_id=? and user_id=?");
			else if (proposalid.startsWith("propv"))
				ps = con
						.prepareStatement("select * from GROUPB_FQ_VehicleProposal where prop_id=? and user_id=?");
			else if (proposalid.startsWith("propl"))
				ps = con
						.prepareStatement("select * from GROUPB_FQ_LiabilityProposal where prop_id=? and user_id=?");
			ps.setString(1, proposalid);
			ps.setString(2, userid);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (proposalid.startsWith("propi")) {
					pro = new BodyInjuryProposal(proposalid, rs.getString(2),
							rs.getDate(3), rs.getDouble(4), rs.getString(6), rs
									.getString(5), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
				} else if (proposalid.startsWith("propv")) {
					pro = new VehicleDamageProposal(proposalid,
							rs.getString(2), rs.getString(3), rs.getDate(4), rs
									.getDouble(5), rs.getString(6), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
				} else if (proposalid.startsWith("propl"))
					pro = new LiabilityProposal(proposalid, rs.getString(2), rs
							.getString(3), rs.getDate(4), rs.getDouble(5), rs
							.getString(6), rs
							.getString(7));
				pro.setInsuranceid(rs.getString(8));
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new FQException(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		return pro;
	}

	public ArrayList<Proposal> mergeSearchProposal(String userid, SearchQuery sq)
			throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proposal pro = null;
		ArrayList<Proposal> result = new ArrayList<Proposal>();
		String query = null;
		try {
			con = DatabaseUtil.getConnection();
			con.setAutoCommit(false);
			if (sq.getContent() != null)
				query = sq.getContent().toString();
			if (sq.getType().equals("Body Injury Proposal")) {
				ps = con
						.prepareStatement("select * from GROUPB_FQ_BodyInjuryProposal where user_id = ?"
								+ query);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				while (rs.next()) {
					pro = new BodyInjuryProposal(rs.getString(1), rs
							.getString(2), rs.getDate(3), rs.getDouble(4), rs
							.getString(6), rs.getString(5), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
					result.add(pro);
				}
			}
			if (sq.getType().equals("Vehicle Damage Proposal")) {
				ps = con
						.prepareStatement("select * from GROUPB_FQ_VehicleProposal where user_id = ?"
								+ query);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				while (rs.next()) {
					pro = new VehicleDamageProposal(rs.getString(1), rs
							.getString(2), rs.getString(3), rs.getDate(4), rs
							.getDouble(5), rs.getString(6), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
					result.add(pro);
				}
			}

			if (sq.getType().equals("Liability Proposal")) {
				ps = con
						.prepareStatement("select * from GROUPB_FQ_LiabilityProposal where user_id = ?"
								+ query);

				ps.setString(1, userid);
				rs = ps.executeQuery();
				while (rs.next()) {
					pro = new LiabilityProposal(rs.getString(1), rs
							.getString(2), rs.getString(3), rs.getDate(4), rs
							.getDouble(5), rs.getString(6), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
					result.add(pro);
				}
			}
			if (sq.getType().equals("All")) {
				ps = con
						.prepareStatement("select * from GROUPB_FQ_BodyInjuryProposal where user_id = ?"
								+ query);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				while (rs.next()) {
					pro = new BodyInjuryProposal(rs.getString(1), rs
							.getString(2), rs.getDate(3), rs.getDouble(4), rs
							.getString(6), rs.getString(5), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
					result.add(pro);
				}
				ps = con
						.prepareStatement("select * from GROUPB_FQ_VehicleProposal where user_id = ?"
								+ query);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				while (rs.next()) {
					pro = new VehicleDamageProposal(rs.getString(1), rs
							.getString(2), rs.getString(3), rs.getDate(4), rs
							.getDouble(5), rs.getString(6), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
					result.add(pro);
				}
				ps = con
						.prepareStatement("select * from GROUPB_FQ_LiabilityProposal where user_id = ?"
								+ query);
				ps.setString(1, userid);
				rs = ps.executeQuery();
				while (rs.next()) {
					pro = new LiabilityProposal(rs.getString(1), rs
							.getString(2), rs.getString(3), rs.getDate(4), rs
							.getDouble(5), rs.getString(6), rs.getString(7));
					pro.setInsuranceid(rs.getString(8));
					result.add(pro);
				}
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new FQException(e1.getMessage());
			}
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		return result;
	}

	public ArrayList<String> getInsuranceType(String userID) throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("select type_of_insurance from GROUPB_FQ_Insurance where user_id=?");
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		return list;
	}
	public boolean login(String userId, String password) throws FQException {
		boolean flag = false;
		String sqlPassword = null;
		String sqlUserId = null;
		Connection con = null;;
		PreparedStatement ps = null;
    	ResultSet rs = null;    	
		try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement("select user_id, password From GROUPB_FQ_login where user_id=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();	
			
			if(rs.next()){
				sqlUserId = rs.getString(1);
				sqlPassword = rs.getString(2);
			    if(userId.equals(sqlUserId) && password.equals(sqlPassword)){
					flag = true;
				}
			}			
			
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			throw new FQException(e.getMessage());
			
		}finally{
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
				
		return flag;
	}
	/**
	 * to find the vehicle by a particular user
	 * @param userID
	 * @return
	 * @throws FQException
	 */
	public ArrayList<String> getVehicleIds(String userID) throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("select vehicle_id from GROUPB_FQ_Vehicle where user_id=? order by vehicle_id");
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		return list;
	}
	/**
	 * find user detail
	 * @param userId
	 * @throws FQException 
	 */
	public User getUserById(String userId) throws FQException {
		// TODO Auto-generated method stub

		Connection con = null;;
		PreparedStatement ps = null;
    	ResultSet rs = null;    	
    	User user = null;
		try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement("select name From GROUPB_FQ_user where user_id=?");
			ps.setString(1, userId);
			rs = ps.executeQuery();	
			if(rs.next()){
				user = new User(userId, rs.getString(1), getInsuranceDetails(userId, null, "bodyinjury"));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FQException(e.getMessage());
			
		}finally{
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
				
		return user;
	}
	
	public ArrayList<Vehicle> getVehiclesByUserId(String userId) throws FQException{
		ArrayList<String> vehicleIdList = getVehicleIds(userId);
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		for(String vid: vehicleIdList){
			vehicleList.add(getVehicleDetails(vid));
		}
		return vehicleList;
	}
	public ArrayList<Insurance> getVehicleInsurancebyUserId(String userId) throws FQException{
		ArrayList<Insurance> insuranceList = new ArrayList<Insurance>();
		for(Vehicle vehicle: getVehiclesByUserId(userId)){
			Insurance insurance1 = getInsuranceDetails(userId, vehicle.getVehicleID(), "vehicledamage");
			Insurance insurance2 = getInsuranceDetails(userId, vehicle.getVehicleID(), "liability");
			if(insurance1!=null)
				insuranceList.add(getInsuranceDetails(userId, vehicle.getVehicleID(), "vehicledamage"));
			if(insurance2!=null)
				insuranceList.add(getInsuranceDetails(userId, vehicle.getVehicleID(), "liability"));
		}
		return insuranceList;
	}
	public double getBalance(String insuranceId)
			throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		double balance = 0;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("select balance from GROUPB_FQ_Insurance where insurance_Id = ?");
			ps.setString(1, insuranceId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				balance = rs.getDouble(1);
			
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return balance;
	}

	public boolean updateAvailableBalance(double availableBalance, String insuranceId) throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		double balance = 0;
		boolean flag = false;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("update GROUPB_FQ_Insurance set available_balance = " +availableBalance+" where insurance_Id = ?");
			ps.setString(1, insuranceId);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
		return flag;
	}
	
	public String getVehicleIDbyLinceseNumber(String ln) throws FQException{
		Connection con = null;;
		PreparedStatement ps = null;
    	ResultSet rs = null;    	
    	String vid = null;
    	try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement("select vehicle_id From GROUPB_FQ_vehicle where car_plate=?");
			ps.setString(1, ln);
			rs = ps.executeQuery();	
			if(rs.next()){
				vid = rs.getString(1);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FQException(e.getMessage());
			
		}finally{
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
    	
    	return vid;
    	
		
	}
	
	public String getLicenseNumberbyVid(String vid) throws FQException{
		Connection con = null;;
		PreparedStatement ps = null;
    	ResultSet rs = null;    	
    	String ln = null;
    	try {
			con = DatabaseUtil.getConnection();
			ps = con.prepareStatement("select car_plate From GROUPB_FQ_vehicle where vehicle_id=?");
			ps.setString(1, vid);
			rs = ps.executeQuery();	
			if(rs.next()){
				ln = rs.getString(1);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new FQException(e.getMessage());
			
		}finally{
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
    	
    	return ln;
    	
		
	}
	
	public ArrayList<String> getLicenseNumber(String userID) throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("select car_plate from GROUPB_FQ_Vehicle where user_id=? order by vehicle_id");
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeConnection(con);
			DatabaseUtil.closeStatement(ps);
		}
		return list;
	}

	public void updateAvailableBalanceToBalance(String insuranceId, double balance) throws FQException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			con = DatabaseUtil.getConnection();
			ps = con
					.prepareStatement("update GROUPB_FQ_Insurance set available_balance ="+balance+" where insurance_Id = ?");
			ps.setString(1, insuranceId);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new FQException(e.getMessage());
		} finally {
			DatabaseUtil.closeStatement(ps);
			DatabaseUtil.closeConnection(con);
		}
	}
}
