package com.dfg.icon.core.v3.service.database.tenant;

public class TenantAwareThread extends Thread {
    private String tenant;

    public TenantAwareThread(String tenant, Runnable target) {
        super(target);
        this.tenant = tenant;
    }

    @Override
    public void run() {
        TenantContext.setTenant(this.tenant);
        super.run();
        TenantContext.clearTenant();
    }

}
