package spring.boot.optic.okulist.dto.admin;

import java.util.List;

public class AdminIdsRequestDto {
    private List<Long> adminIds;

    public List<Long> getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(List<Long> adminIds) {
        this.adminIds = adminIds;
    }
}
