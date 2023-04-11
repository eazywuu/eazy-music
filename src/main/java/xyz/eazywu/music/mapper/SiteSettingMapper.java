package xyz.eazywu.music.mapper;

import org.mapstruct.Mapper;
import xyz.eazywu.music.object.dto.SiteSettingDto;
import xyz.eazywu.music.object.vo.SiteSettingVo;

@Mapper(componentModel = "spring")
public interface SiteSettingMapper {
    SiteSettingVo toVo(SiteSettingDto siteSetting);
}
