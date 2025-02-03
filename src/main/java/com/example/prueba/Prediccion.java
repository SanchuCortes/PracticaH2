package com.example.prueba;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediccion {

    @JsonProperty("predConcello")
    private PredConcello predConcello;

    // Getter y setter
    public PredConcello getPredConcello() {
        return predConcello;
    }

    public void setPredConcello(PredConcello predConcello) {
        this.predConcello = predConcello;
    }

    // Clase interna PredConcello
    public static class PredConcello {
        @JsonProperty("idConcello")
        private int idConcello;

        @JsonProperty("nome")
        private String nome;

        @JsonProperty("listaPredDiaConcello")
        private List<PredDiaConcello> listaPredDiaConcello;

        // Getter y setter
        public int getIdConcello() {
            return idConcello;
        }

        public void setIdConcello(int idConcello) {
            this.idConcello = idConcello;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public List<PredDiaConcello> getListaPredDiaConcello() {
            return listaPredDiaConcello;
        }

        public void setListaPredDiaConcello(List<PredDiaConcello> listaPredDiaConcello) {
            this.listaPredDiaConcello = listaPredDiaConcello;
        }

        // Clase interna PredDiaConcello
        public static class PredDiaConcello {
            @JsonProperty("dataPredicion")
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            private String dataPredicion;

            @JsonProperty("nivelAviso")
            private String nivelAviso;

            @JsonProperty("tMax")
            private int tMax;

            @JsonProperty("tMin")
            private int tMin;

            @JsonProperty("tmaxFranxa")
            private TempFranxa tmaxFranxa;

            @JsonProperty("tminFranxa")
            private TempFranxa tminFranxa;

            @JsonProperty("ceo")
            private Ceo ceo;

            @JsonProperty("pchoiva")
            private Pchoiva pchoiva;

            @JsonProperty("uvMax")
            private int uvMax;

            @JsonProperty("vento")
            private Vento vento;

            // Getters y setters
            public String getDataPredicion() {
                return dataPredicion;
            }

            public void setDataPredicion(String dataPredicion) {
                this.dataPredicion = dataPredicion;
            }

            public String getNivelAviso() {
                return nivelAviso;
            }

            public void setNivelAviso(String nivelAviso) {
                this.nivelAviso = nivelAviso;
            }

            public int gettMax() {
                return tMax;
            }

            public void settMax(int tMax) {
                this.tMax = tMax;
            }

            public int gettMin() {
                return tMin;
            }

            public void settMin(int tMin) {
                this.tMin = tMin;
            }

            public TempFranxa getTmaxFranxa() {
                return tmaxFranxa;
            }

            public void setTmaxFranxa(TempFranxa tmaxFranxa) {
                this.tmaxFranxa = tmaxFranxa;
            }

            public TempFranxa getTminFranxa() {
                return tminFranxa;
            }

            public void setTminFranxa(TempFranxa tminFranxa) {
                this.tminFranxa = tminFranxa;
            }

            public Ceo getCeo() {
                return ceo;
            }

            public void setCeo(Ceo ceo) {
                this.ceo = ceo;
            }

            public Pchoiva getPchoiva() {
                return pchoiva;
            }

            public void setPchoiva(Pchoiva pchoiva) {
                this.pchoiva = pchoiva;
            }

            public int getUvMax() {
                return uvMax;
            }

            public void setUvMax(int uvMax) {
                this.uvMax = uvMax;
            }

            public Vento getVento() {
                return vento;
            }

            public void setVento(Vento vento) {
                this.vento = vento;
            }
        }

        // Clase interna TempFranxa
        public static class TempFranxa {
            @JsonProperty("manha")
            private int manha;

            @JsonProperty("noite")
            private int noite;

            @JsonProperty("tarde")
            private int tarde;

            // Getters y setters
            public int getManha() {
                return manha;
            }

            public void setManha(int manha) {
                this.manha = manha;
            }

            public int getNoite() {
                return noite;
            }

            public void setNoite(int noite) {
                this.noite = noite;
            }

            public int getTarde() {
                return tarde;
            }

            public void setTarde(int tarde) {
                this.tarde = tarde;
            }
        }

        // Clase interna Ceo
        public static class Ceo {
            @JsonProperty("manha")
            private int manha;

            @JsonProperty("noite")
            private int noite;

            @JsonProperty("tarde")
            private int tarde;

            // Getters y setters
            public int getManha() {
                return manha;
            }

            public void setManha(int manha) {
                this.manha = manha;
            }

            public int getNoite() {
                return noite;
            }

            public void setNoite(int noite) {
                this.noite = noite;
            }

            public int getTarde() {
                return tarde;
            }

            public void setTarde(int tarde) {
                this.tarde = tarde;
            }
        }

        // Clase interna Pchoiva
        public static class Pchoiva {
            @JsonProperty("manha")
            private int manha;

            @JsonProperty("noite")
            private int noite;

            @JsonProperty("tarde")
            private int tarde;

            // Getters y setters
            public int getManha() {
                return manha;
            }

            public void setManha(int manha) {
                this.manha = manha;
            }

            public int getNoite() {
                return noite;
            }

            public void setNoite(int noite) {
                this.noite = noite;
            }

            public int getTarde() {
                return tarde;
            }

            public void setTarde(int tarde) {
                this.tarde = tarde;
            }
        }

        // Clase interna Vento
        public static class Vento {
            @JsonProperty("manha")
            private int manha;

            @JsonProperty("noite")
            private int noite;

            @JsonProperty("tarde")
            private int tarde;

            // Getters y setters
            public int getManha() {
                return manha;
            }

            public void setManha(int manha) {
                this.manha = manha;
            }

            public int getNoite() {
                return noite;
            }

            public void setNoite(int noite) {
                this.noite = noite;
            }

            public int getTarde() {
                return tarde;
            }

            public void setTarde(int tarde) {
                this.tarde = tarde;
            }
        }
    }
}
