package br.com.bzet.cardapio.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.ParameterizedType;

/**
 * Classe do Objeto Item do Cardapio
 */
public class Item implements Parcelable{

    private String nome;
    private String preco;
    private String descricao;
    private Integer imagemUrl;
    private String tipo;
    private Boolean ativo;

    public Item(String nome, String preco, String descricao, Integer imagemUrl, String tipo, Boolean ativo) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(Integer imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    // PARCELABLE
    public Item(Parcel parcel){
        setNome(parcel.readString());
        setPreco(parcel.readString());
        setDescricao(parcel.readString());
        setImagemUrl(parcel.readInt());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( getNome() );
        dest.writeString( getPreco() );
        dest.writeString( getDescricao() );
        dest.writeInt( getImagemUrl() );
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>(){
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }
        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

