/*
 * Created on 23 mai 2017 ( Time 18:19:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package br.com.ibrowse.bean.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent class for entity stored in table "CONTAS_PAGAR_RATEIO"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="CONTAS_PAGAR_RATEIO", schema="DB_FINANCEIRO" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ContasPagarRateioEntity.countAll", query="SELECT COUNT(x) FROM ContasPagarRateioEntity x" )
} )
public class ContasPagarRateioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="OID_CONTAS_PAGAR_RATEIO", nullable=false)
    private Long oidContasPagarRateio ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="OID_CONTAS_PAGAR", nullable=false, length=100)
    private String     oidContasPagar ;

    @Column(name="VR_RATEIO", nullable=false)
    private Long vrRateio     ;

    @Column(name="DS_USU_ALTER", nullable=false, length=30)
    private String     dsUsuAlter   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_ULT_ALTER", nullable=false)
    private Date       dtUltAlter   ;

    @Column(name="VS_VERSAO", nullable=false)
    private Long vsVersao     ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="OID_CENTROS_CUSTOS", referencedColumnName="OID_CENTROS_CUSTOS", nullable=false)
    private CentrosCustosEntity centrosCustos ;
    
    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ContasPagarRateioEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setOidContasPagarRateio( Long oidContasPagarRateio ) {
        this.oidContasPagarRateio = oidContasPagarRateio ;
    }
    public Long getOidContasPagarRateio() {
        return this.oidContasPagarRateio;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : OID_CONTAS_PAGAR ( VARCHAR2 ) 
    public void setOidContasPagar( String oidContasPagar ) {
        this.oidContasPagar = oidContasPagar;
    }
    public String getOidContasPagar() {
        return this.oidContasPagar;
    }

    //--- DATABASE MAPPING : VR_RATEIO ( NUMBER ) 
    public void setVrRateio( Long vrRateio ) {
        this.vrRateio = vrRateio;
    }
    public Long getVrRateio() {
        return this.vrRateio;
    }

    //--- DATABASE MAPPING : DS_USU_ALTER ( VARCHAR2 ) 
    public void setDsUsuAlter( String dsUsuAlter ) {
        this.dsUsuAlter = dsUsuAlter;
    }
    public String getDsUsuAlter() {
        return this.dsUsuAlter;
    }

    //--- DATABASE MAPPING : DT_ULT_ALTER ( DATE ) 
    public void setDtUltAlter( Date dtUltAlter ) {
        this.dtUltAlter = dtUltAlter;
    }
    public Date getDtUltAlter() {
        return this.dtUltAlter;
    }

    //--- DATABASE MAPPING : VS_VERSAO ( NUMBER ) 
    public void setVsVersao( Long vsVersao ) {
        this.vsVersao = vsVersao;
    }
    public Long getVsVersao() {
        return this.vsVersao;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public CentrosCustosEntity getCentrosCustos() {
		return centrosCustos;
	}

	public void setCentrosCustos(CentrosCustosEntity centrosCustos) {
		this.centrosCustos = centrosCustos;
	}
    
    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(oidContasPagarRateio);
        sb.append("]:"); 
        sb.append(oidContasPagar);
        sb.append("|");
        sb.append(vrRateio);
        sb.append("|");
        sb.append(dsUsuAlter);
        sb.append("|");
        sb.append(dtUltAlter);
        sb.append("|");
        sb.append(vsVersao);
        return sb.toString(); 
    }

}
