-- Table: public.TX_DENDA_KETERLAMBATAN

-- DROP TABLE IF EXISTS public."TX_DENDA_KETERLAMBATAN";

CREATE TABLE IF NOT EXISTS public."TX_DENDA_KETERLAMBATAN"
(
    id_denda character varying COLLATE pg_catalog."default" NOT NULL,
    no_transaksi character varying COLLATE pg_catalog."default" NOT NULL,
    cicilan_ke numeric NOT NULL,
    tgl_denda date NOT NULL,
    biaya_denda numeric NOT NULL,
    tgl_pembayaran_denda date,
    no_pembayaran character varying COLLATE pg_catalog."default",
    CONSTRAINT "TX_DENDA_KETERLAMBATAN_pkey" PRIMARY KEY (id_denda)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."TX_DENDA_KETERLAMBATAN"
    OWNER to postgres;