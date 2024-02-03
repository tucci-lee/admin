package cc.tucci.admin.app.crontab.exe;

import cc.tucci.admin.app.crontab.assembler.CrontabAssembler;
import cc.tucci.admin.app.crontab.dto.vo.CrontabVO;
import cc.tucci.admin.domain.core.dto.PageResponse;
import cc.tucci.admin.domain.core.dto.Response;
import cc.tucci.admin.domain.crontab.dataobject.CrontabDO;
import cc.tucci.admin.app.crontab.dto.query.CrontabQuery;
import cc.tucci.admin.domain.crontab.mapper.CrontabMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tucci
 */
@Service
public class CrontabListExe {

    private final CrontabMapper crontabMapper;

    public CrontabListExe(CrontabMapper crontabMapper) {
        this.crontabMapper = crontabMapper;
    }

    public Response execute(CrontabQuery query) {
        Page<CrontabDO> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<CrontabDO> wrapper = Wrappers.lambdaQuery(CrontabDO.class)
                .likeLeft(!StringUtils.isEmpty(query.getName()), CrontabDO::getName, query.getName())
                .eq(query.getStatus() != null, CrontabDO::getStatus, query.getStatus())
                .eq(CrontabDO::getIsDeleted, false)
                .orderByDesc(CrontabDO::getCreateTime);
        crontabMapper.selectPage(page, wrapper);
        List<CrontabVO> voList = page.getRecords().stream()
                .map(CrontabAssembler::toVO)
                .collect(Collectors.toList());
        return PageResponse.success(voList, (int) page.getTotal());
    }
}
