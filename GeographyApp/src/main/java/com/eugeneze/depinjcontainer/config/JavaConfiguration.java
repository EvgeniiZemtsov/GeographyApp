package com.eugeneze.depinjcontainer.config;

public class JavaConfiguration implements Configuration {
    @Override
    public String getPackageToScan() {
        return "com.eugeneze";
    }
}
