package com.lan.my_app;

import java.util.List;

public class ASRresponse {
    private List<String> results_recognition;
    private String result_type;
    private String best_result;
    private int error;
    private OriginResultBean origin_result;

    public String getResult_type(){
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public int getError() {
        return error;
    }

    public List<String> getResults_recognition() {
        return results_recognition;
    }

    public OriginResultBean getOrigin_result() {
        return origin_result;
    }

    public String getBest_result() {
        return best_result;
    }

    public void setBest_result(String best_result) {
        this.best_result = best_result;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setOrigin_result(OriginResultBean origin_result) {
        this.origin_result = origin_result;
    }

    public void setResults_recognition(List<String> results_recognition) {
        this.results_recognition = results_recognition;
    }
    public static class OriginResultBean{
        private int asr_align_begin;
        private int asr_align_end;
        private long corpus_no;
        private int err_no;
        private int raf;
        private ResultBean result;
        private String sn;

        public ResultBean getResult() {
            return result;
        }

        public int getAsr_align_begin() {
            return asr_align_begin;
        }

        public int getAsr_align_end() {
            return asr_align_end;
        }

        public int getErr_no() {
            return err_no;
        }

        public int getRaf() {
            return raf;
        }

        public long getCorpus_no() {
            return corpus_no;
        }

        public String getSn() {
            return sn;
        }

        public void setAsr_align_begin(int asr_align_begin) {
            this.asr_align_begin = asr_align_begin;
        }

        public void setAsr_align_end(int asr_align_end) {
            this.asr_align_end = asr_align_end;
        }

        public void setCorpus_no(long corpus_no) {
            this.corpus_no = corpus_no;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public void setRaf(int raf) {
            this.raf = raf;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }
        public static class ResultBean{
            private List<String> word;

            public List<String> getWord() {
                return word;
            }

            public void setWord(List<String> word) {
                this.word = word;
            }
        }
    }
}
