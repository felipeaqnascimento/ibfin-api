/*
 * Created on 23 mai 2017 ( Time 18:19:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package br.com.ibrowse.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContasReceber implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Long oidContasReceber;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    @Size( min = 1, max = 100 )
    private String nrDocumentoFiscal;

    @NotNull
    private Long oidPessoas;

    @NotNull
    private Date dtPrevisao;

    @NotNull
    private Date dtVencimento;

    @NotNull
    private Date dtRecebimento;

    @NotNull
    private Long vrBruto;


    private Long vrJuros;


    private Long vrDesconto;


    private Long oidCentrosCustos;


    private Long oidBancos;

    @Size( max = 20 )
    private String dsFormaRecebimento;

    @Size( max = 500 )
    private String dsObservacao;

    @NotNull
    private Long vrBaseInss;

    @NotNull
    private Long vrDeducaoInss;

    @NotNull
    private Long vrBaseIssqn;

    @NotNull
    private Long vrDeducaoIssqn;

    @NotNull
    private Long vrBaseCsll;

    @NotNull
    private Long vrDeducaoCsll;

    @NotNull
    private Long vrBasePis;

    @NotNull
    private Long vrDeducaoPis;

    @NotNull
    private Long vrBaseCofins;

    @NotNull
    private Long vrDeducaoCofins;

    @NotNull
    private Long vrBaseIrpj;

    @NotNull
    private Long vrDeducaoIrpj;

    @NotNull
    private Long vrOutrasDeducoes;

    @NotNull
    @Size( min = 1, max = 30 )
    private String dsUsuAlter = "IBROWSE";

    @NotNull
    private Date dtUltAlter = new Date();

    @NotNull
    private Long vsVersao = 0L;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setOidContasReceber( Long oidContasReceber ) {
        this.oidContasReceber = oidContasReceber ;
    }

    public Long getOidContasReceber() {
        return this.oidContasReceber;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setNrDocumentoFiscal( String nrDocumentoFiscal ) {
        this.nrDocumentoFiscal = nrDocumentoFiscal;
    }
    public String getNrDocumentoFiscal() {
        return this.nrDocumentoFiscal;
    }

    public void setOidPessoas( Long oidPessoas ) {
        this.oidPessoas = oidPessoas;
    }
    public Long getOidPessoas() {
        return this.oidPessoas;
    }

    public void setDtPrevisao( Date dtPrevisao ) {
        this.dtPrevisao = dtPrevisao;
    }
    public Date getDtPrevisao() {
        return this.dtPrevisao;
    }

    public void setDtVencimento( Date dtVencimento ) {
        this.dtVencimento = dtVencimento;
    }
    public Date getDtVencimento() {
        return this.dtVencimento;
    }

    public void setDtRecebimento( Date dtRecebimento ) {
        this.dtRecebimento = dtRecebimento;
    }
    public Date getDtRecebimento() {
        return this.dtRecebimento;
    }

    public void setVrBruto( Long vrBruto ) {
        this.vrBruto = vrBruto;
    }
    public Long getVrBruto() {
        return this.vrBruto;
    }

    public void setVrJuros( Long vrJuros ) {
        this.vrJuros = vrJuros;
    }
    public Long getVrJuros() {
        return this.vrJuros;
    }

    public void setVrDesconto( Long vrDesconto ) {
        this.vrDesconto = vrDesconto;
    }
    public Long getVrDesconto() {
        return this.vrDesconto;
    }

    public void setOidCentrosCustos( Long oidCentrosCustos ) {
        this.oidCentrosCustos = oidCentrosCustos;
    }
    public Long getOidCentrosCustos() {
        return this.oidCentrosCustos;
    }

    public void setOidBancos( Long oidBancos ) {
        this.oidBancos = oidBancos;
    }
    public Long getOidBancos() {
        return this.oidBancos;
    }

    public void setDsFormaRecebimento( String dsFormaRecebimento ) {
        this.dsFormaRecebimento = dsFormaRecebimento;
    }
    public String getDsFormaRecebimento() {
        return this.dsFormaRecebimento;
    }

    public void setDsObservacao( String dsObservacao ) {
        this.dsObservacao = dsObservacao;
    }
    public String getDsObservacao() {
        return this.dsObservacao;
    }

    public void setVrBaseInss( Long vrBaseInss ) {
        this.vrBaseInss = vrBaseInss;
    }
    public Long getVrBaseInss() {
        return this.vrBaseInss;
    }

    public void setVrDeducaoInss( Long vrDeducaoInss ) {
        this.vrDeducaoInss = vrDeducaoInss;
    }
    public Long getVrDeducaoInss() {
        return this.vrDeducaoInss;
    }

    public void setVrBaseIssqn( Long vrBaseIssqn ) {
        this.vrBaseIssqn = vrBaseIssqn;
    }
    public Long getVrBaseIssqn() {
        return this.vrBaseIssqn;
    }

    public void setVrDeducaoIssqn( Long vrDeducaoIssqn ) {
        this.vrDeducaoIssqn = vrDeducaoIssqn;
    }
    public Long getVrDeducaoIssqn() {
        return this.vrDeducaoIssqn;
    }

    public void setVrBaseCsll( Long vrBaseCsll ) {
        this.vrBaseCsll = vrBaseCsll;
    }
    public Long getVrBaseCsll() {
        return this.vrBaseCsll;
    }

    public void setVrDeducaoCsll( Long vrDeducaoCsll ) {
        this.vrDeducaoCsll = vrDeducaoCsll;
    }
    public Long getVrDeducaoCsll() {
        return this.vrDeducaoCsll;
    }

    public void setVrBasePis( Long vrBasePis ) {
        this.vrBasePis = vrBasePis;
    }
    public Long getVrBasePis() {
        return this.vrBasePis;
    }

    public void setVrDeducaoPis( Long vrDeducaoPis ) {
        this.vrDeducaoPis = vrDeducaoPis;
    }
    public Long getVrDeducaoPis() {
        return this.vrDeducaoPis;
    }

    public void setVrBaseCofins( Long vrBaseCofins ) {
        this.vrBaseCofins = vrBaseCofins;
    }
    public Long getVrBaseCofins() {
        return this.vrBaseCofins;
    }

    public void setVrDeducaoCofins( Long vrDeducaoCofins ) {
        this.vrDeducaoCofins = vrDeducaoCofins;
    }
    public Long getVrDeducaoCofins() {
        return this.vrDeducaoCofins;
    }

    public void setVrBaseIrpj( Long vrBaseIrpj ) {
        this.vrBaseIrpj = vrBaseIrpj;
    }
    public Long getVrBaseIrpj() {
        return this.vrBaseIrpj;
    }

    public void setVrDeducaoIrpj( Long vrDeducaoIrpj ) {
        this.vrDeducaoIrpj = vrDeducaoIrpj;
    }
    public Long getVrDeducaoIrpj() {
        return this.vrDeducaoIrpj;
    }

    public void setVrOutrasDeducoes( Long vrOutrasDeducoes ) {
        this.vrOutrasDeducoes = vrOutrasDeducoes;
    }
    public Long getVrOutrasDeducoes() {
        return this.vrOutrasDeducoes;
    }

    public void setDsUsuAlter( String dsUsuAlter ) {
        this.dsUsuAlter = dsUsuAlter;
    }
    public String getDsUsuAlter() {
        return this.dsUsuAlter;
    }

    public void setDtUltAlter( Date dtUltAlter ) {
        this.dtUltAlter = dtUltAlter;
    }
    public Date getDtUltAlter() {
        return this.dtUltAlter;
    }

    public void setVsVersao( Long vsVersao ) {
        this.vsVersao = vsVersao;
    }
    public Long getVsVersao() {
        return this.vsVersao;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(oidContasReceber);
        sb.append("|");
        sb.append(nrDocumentoFiscal);
        sb.append("|");
        sb.append(oidPessoas);
        sb.append("|");
        sb.append(dtPrevisao);
        sb.append("|");
        sb.append(dtVencimento);
        sb.append("|");
        sb.append(dtRecebimento);
        sb.append("|");
        sb.append(vrBruto);
        sb.append("|");
        sb.append(vrJuros);
        sb.append("|");
        sb.append(vrDesconto);
        sb.append("|");
        sb.append(oidCentrosCustos);
        sb.append("|");
        sb.append(oidBancos);
        sb.append("|");
        sb.append(dsFormaRecebimento);
        sb.append("|");
        sb.append(dsObservacao);
        sb.append("|");
        sb.append(vrBaseInss);
        sb.append("|");
        sb.append(vrDeducaoInss);
        sb.append("|");
        sb.append(vrBaseIssqn);
        sb.append("|");
        sb.append(vrDeducaoIssqn);
        sb.append("|");
        sb.append(vrBaseCsll);
        sb.append("|");
        sb.append(vrDeducaoCsll);
        sb.append("|");
        sb.append(vrBasePis);
        sb.append("|");
        sb.append(vrDeducaoPis);
        sb.append("|");
        sb.append(vrBaseCofins);
        sb.append("|");
        sb.append(vrDeducaoCofins);
        sb.append("|");
        sb.append(vrBaseIrpj);
        sb.append("|");
        sb.append(vrDeducaoIrpj);
        sb.append("|");
        sb.append(vrOutrasDeducoes);
        sb.append("|");
        sb.append(dsUsuAlter);
        sb.append("|");
        sb.append(dtUltAlter);
        sb.append("|");
        sb.append(vsVersao);
        return sb.toString(); 
    } 


}