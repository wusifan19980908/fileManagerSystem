package com.wusifan.filemanagersystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wusifan.filemanagersystem.dao.SysMenuDao;
import com.wusifan.filemanagersystem.model.SysMenu;
import com.wusifan.filemanagersystem.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * @author jilin
 * @date 2021/06/16 10:26
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
}
