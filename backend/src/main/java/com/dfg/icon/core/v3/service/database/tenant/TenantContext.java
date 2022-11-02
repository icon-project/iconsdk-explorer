package com.dfg.icon.core.v3.service.database.tenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TenantContext {
    private static final Logger logger = LoggerFactory.getLogger(TenantContext.class);
    public static final String DEFAULT_DATABASE = "explorer";

    private static final ThreadLocal<Object> tenantHolder = new ThreadLocal<>();

    public static Object getTenant() {
        Object tenant = tenantHolder.get();
        return Objects.isNull(tenant) ? DEFAULT_DATABASE : tenant;
    }

    public static void setTenant(Object tenant) {
        tenantHolder.set(tenant);
    }

    public static void setDefaultTenant() {
        tenantHolder.set(DEFAULT_DATABASE);
    }

    public static void clearTenant() {
        tenantHolder.remove();
    }

}
