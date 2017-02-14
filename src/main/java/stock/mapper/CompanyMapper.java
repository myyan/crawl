package stock.mapper;

import stock.po.Company;

import java.util.List;

/**
 * Created by heiqie on 2017/1/19.
 */
public interface CompanyMapper {
    int insert(Company company);

    void delete(int id);

    Company selectOne(int id);

    List<Company> selectAll();

    int update(Company company);

    int insertAll(List<Company> companies);

    Company selectByCode(String companyCode);
}
