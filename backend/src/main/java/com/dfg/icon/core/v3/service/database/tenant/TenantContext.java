package com.dfg.icon.core.v3.service.database.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TenantContext {
    private static final Logger logger = LoggerFactory.getLogger(TenantContext.class);
    public static final String DEFAULT_DATABASE = "explorer";

    private static final ThreadLocal<String> tenantHolder = new ThreadLocal<>();

    public static String getTenant() {
        String tenant = tenantHolder.get();
        return Objects.isNull(tenant) ? DEFAULT_DATABASE : tenant;
    }

    public static void setTenant(String tenant) {
        tenantHolder.set(tenant);
    }

    public static void setDefaultTenant() {
        tenantHolder.set(DEFAULT_DATABASE);
    }

    public static void clearTenant() {
        tenantHolder.remove();
    }

}
