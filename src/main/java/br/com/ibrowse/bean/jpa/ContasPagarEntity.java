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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent class for entity stored in table "CONTAS_PAGAR"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="CONTAS_PAGAR", schema="DB_FINANCEIRO" )
@SequenceGenerator(name="SE_CONTAS_PAGAR", sequenceName="SE_CONTAS_PAGAR")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ContasPagarEntity.countAll", query="SELECT COUNT(x) FROM ContasPagarEntity x" )
} )
public class ContasPagarEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id @GeneratedValue(strategy=GenerationType.AUTO, generator="SE_CONTAS_PAGAR")
    @Column(name="OID_CONTAS_PAGAR", nullable=false)
    private Long oidContasPagar ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="NR_DOCUMENTO_FISCAL", nullable=false, length=100)
    private String     nrDocumentoFiscal ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_PREVISAO", nullable=false)
    private Date       dtPrevisao   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_VENCIMENTO", nullable=false)
    private Date       dtVencimento ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_PAGAMENTO")
    private Date       dtPagamento  ;

    @Column(name="VR_BRUTO", nullable=false)
    private Long vrBruto      ;

    @Column(name="VR_JUROS")
    private Long vrJuros      ;

    @Column(name="VR_DESCONTO")
    private Long vrDesconto   ;

    @Column(name="DS_FORMA_PAGAMENTO", length=20)
    private String     dsFormaPagamento ;

    @Column(name="DS_OBSERVACAO", length=500)
    private String     dsObservacao ;

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
    @JoinColumn(name="OID_PESSOAS", referencedColumnName="OID_PESSOAS")
    private PessoasEntity pessoas;
    
    @ManyToOne
    @JoinColumn(name="OID_TIPOS_TITULOS", referencedColumnName="OID_TIPOS_TITULOS")
    private TiposTitulosEntity tiposTitulos;
    
    @ManyToOne
    @JoinColumn(name="OID_BANCOS", referencedColumnName="OID_BANCOS")
    private BancosEntity bancos;
    
    @ManyToOne
    @JoinColumn(name="OID_CENTROS_CUSTOS", referencedColumnName="OID_CENTROS_CUSTOS")
    private CentrosCustosEntity centrosCustos;
    
    //----------------------------------------------------------------------
    // TRANSIENTS
    //----------------------------------------------------------------------
    private transient boolean idRateio = false;
    private transient Long nrVezesRateio;
    
    
    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ContasPagarEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setOidContasPagar( Long oidContasPagar ) {
        this.oidContasPagar = oidContasPagar ;
    }
    public Long getOidContasPagar() {
        return this.oidContasPagar;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : NR_DOCUMENTO_FISCAL ( VARCHAR2 ) 
    public void setNrDocumentoFiscal( String nrDocumentoFiscal ) {
        this.nrDocumentoFiscal = nrDocumentoFiscal;
    }
    public String getNrDocumentoFiscal() {
        return this.nrDocumentoFiscal;
    }

    //--- DATABASE MAPPING : DT_PREVISAO ( DATE ) 
    public void setDtPrevisao( Date dtPrevisao ) {
        this.dtPrevisao = dtPrevisao;
    }
    public Date getDtPrevisao() {
        return this.dtPrevisao;
    }

    //--- DATABASE MAPPING : DT_VENCIMENTO ( DATE ) 
    public void setDtVencimento( Date dtVencimento ) {
        this.dtVencimento = dtVencimento;
    }
    public Date getDtVencimento() {
        return this.dtVencimento;
    }

    //--- DATABASE MAPPING : DT_PAGAMENTO ( DATE ) 
    public void setDtPagamento( Date dtPagamento ) {
        this.dtPagamento = dtPagamento;
    }
    public Date getDtPagamento() {
        return this.dtPagamento;
    }

    //--- DATABASE MAPPING : VR_BRUTO ( NUMBER ) 
    public void setVrBruto( Long vrBruto ) {
        this.vrBruto = vrBruto;
    }
    public Long getVrBruto() {
        return this.vrBruto;
    }

    //--- DATABASE MAPPING : VR_JUROS ( NUMBER ) 
    public void setVrJuros( Long vrJuros ) {
        this.vrJuros = vrJuros;
    }
    public Long getVrJuros() {
        return this.vrJuros;
    }

    //--- DATABASE MAPPING : VR_DESCONTO ( NUMBER ) 
    public void setVrDesconto( Long vrDesconto ) {
        this.vrDesconto = vrDesconto;
    }
    public Long getVrDesconto() {
        return this.vrDesconto;
    }

    //--- DATABASE MAPPING : DS_FORMA_PAGAMENTO ( VARCHAR2 ) 
    public void setDsFormaPagamento( String dsFormaPagamento ) {
        this.dsFormaPagamento = dsFormaPagamento;
    }
    public String getDsFormaPagamento() {
        return this.dsFormaPagamento;
    }

    //--- DATABASE MAPPING : DS_OBSERVACAO ( VARCHAR2 ) 
    public void setDsObservacao( String dsObservacao ) {
        this.dsObservacao = dsObservacao;
    }
    public String getDsObservacao() {
        return this.dsObservacao;
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
    public PessoasEntity getPessoas() {
		return pessoas;
	}

	public void setPessoas(PessoasEntity pessoas) {
		this.pessoas = pessoas;
	}
    
    public TiposTitulosEntity getTiposTitulos() {
		return tiposTitulos;
	}

	public void setTiposTitulos(TiposTitulosEntity tiposTitulos) {
		this.tiposTitulos = tiposTitulos;
	}
	
	public BancosEntity getBancos() {
		return bancos;
	}

	public void setBancos(BancosEntity bancos) {
		this.bancos = bancos;
	}
	
	public CentrosCustosEntity getCentrosCustos() {
		return centrosCustos;
	}

	public void setCentrosCustos(CentrosCustosEntity centrosCustos) {
		this.centrosCustos = centrosCustos;
	}

	//----------------------------------------------------------------------
    // GETTER & SETTER FOR TRANSIENTS
    //----------------------------------------------------------------------
	public boolean getIdRateio() {
		return idRateio;
	}
	public void setIdRateio(boolean idRateio) {
		this.idRateio = idRateio;
	}

	public Long getNrVezesRateio() {
		return nrVezesRateio;
	}
	public void setNrVezesRateio(Long nrVezesRateio) {
		this.nrVezesRateio = nrVezesRateio;
	}
	
	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(oidContasPagar);
        sb.append("]:"); 
        sb.append(nrDocumentoFiscal);
        sb.append("|");
        sb.append(dtPrevisao);
        sb.append("|");
        sb.append(dtVencimento);
        sb.append("|");
        sb.append(dtPagamento);
        sb.append("|");
        sb.append(vrBruto);
        sb.append("|");
        sb.append(vrJuros);
        sb.append("|");
        sb.append(vrDesconto);
        sb.append("|");
        sb.append(dsFormaPagamento);
        sb.append("|");
        sb.append(dsObservacao);
        sb.append("|");
        sb.append(dsUsuAlter);
        sb.append("|");
        sb.append(dtUltAlter);
        sb.append("|");
        sb.append(vsVersao);
        return sb.toString(); 
    }

}
