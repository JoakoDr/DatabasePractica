package com.example.joaco.databasepractica;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;




    @Entity(tableName = "class")
    public class ModelClass {

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "Year")
        private String year;

        @ColumnInfo(name = "Name")
        private String name;
        @NonNull
        @ColumnInfo(name = "People")
        private String nPeople;
        @NonNull
        @ColumnInfo(name = "Teacher")
        private String tutor;
        @NonNull
        @ColumnInfo(name = "Average")
        private String average;
        public ModelClass(@NonNull String year,String name,String people,String tutor,String average) {
            this.year = year;
            this.name = name;
            this.nPeople = nPeople;
            this.tutor = tutor;
            this.average = average;
        }
        public String getYear(){
        return this.year;
        }
        public String getName(){
            return this.name;
        }
        public String getPeople(){
            return this.nPeople;
        }
        public String getTeacher(){
            return this.tutor;
        }
        public String getAverage(){
            return this.average;
        }
    }


