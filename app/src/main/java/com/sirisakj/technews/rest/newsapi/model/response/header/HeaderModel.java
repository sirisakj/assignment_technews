package com.sirisakj.technews.rest.newsapi.model.response.header;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by icsme on 15-Jul-17.
 */

public class HeaderModel {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("sortBy")
        @Expose
        private String sortBy;


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSortBy() {
            return sortBy;
        }

        public void setSortBy(String sortBy) {
            this.sortBy = sortBy;
        }


}
