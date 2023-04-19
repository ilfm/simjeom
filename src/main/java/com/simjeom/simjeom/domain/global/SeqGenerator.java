package com.simjeom.simjeom.domain.global;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;


/**
 * The type Seq generator.
 */
@Slf4j
public class SeqGenerator implements IdentifierGenerator {

  public static final String SEQ_NAME = "SEQ_NAME";
  public static final String PREFIX = "PREFIX";

  private String seqName;     // 시퀀스이름
  private String prefix;      //

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {
    IdentifierGenerator.super.configure(type, params, serviceRegistry);
    this.seqName = ConfigurationHelper.getString(SEQ_NAME, params);
    this.prefix = ConfigurationHelper.getString(PREFIX, params);
    log.debug("seqName1={}",seqName);
    log.debug("prefix1={}",prefix);
    System.out.println("seqName = " + seqName);
    System.out.println("prefix = " + prefix);
  }

  @Override
  public Object generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {

    String sql = null;
    String newSeq = "";
    // JDBC Connection
    Connection con = null;

    try {
      con = session.getJdbcConnectionAccess().obtainConnection();  // 공유세션으로부터 jdbc connection을 얻는다
      String dbDriverName = con.getMetaData().getDriverName();     // DB정보

      if (dbDriverName.contains("H2")) {
        sql = "CALL NEXT VALUE FOR " + this.seqName;        //H2
      } else if (dbDriverName.contains("MariaDB")) {
        sql = "SELECT NEXTVAL(" + this.seqName + ")";     // 마리아 디비
      }
      log.debug("sql@@@@@@@={}",sql);
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql); // SQL를 실행

      if (rs.next()) {
        newSeq = this.prefix + "-" + rs.getString(1); // 결과값 추출
      }
      log.debug("newSeq={}",newSeq);
    } catch (SQLException sqlException) {
      throw new HibernateException(sqlException);
    } finally {
      try {
        if (!con.isClosed()) {
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return newSeq;
  }
}
