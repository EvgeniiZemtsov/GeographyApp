package com.eugeneze.study.depinjcontainer.config;

public class JavaConfiguration implements Configuration {
    @Override
    public String getPackageToScan() {
        return "com.eugeneze";
    }
}
