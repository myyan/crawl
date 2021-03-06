package stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.mapper.CompanyMapper;
import stock.po.Company;

import java.util.List;

/**
 * Created by heiqie on 2017/1/19.
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper mapper;

    public int insert(Company company) {
        return mapper.insert(company);
    }

    public Company selectOne(int id) {
        return mapper.selectOne(id);
    }

    public List<Company> selectAll() {
        return mapper.selectAll();
    }

    public void delete(int id) {
        mapper.delete(id);
    }

    public int update(Company company) {
        return mapper.update(company);
    }

    public int insertAll(List<Company> companies) {
        return mapper.insertAll(companies);
    }

    public Company selectByCode(String companyCode) {
        return mapper.selectByCode(companyCode);
    }

    public List<Company> selectByType(int type) {
        return mapper.selectByType(type);
    }

    public int batchUpdate(List<Company> companies){
        return mapper.batchUpdate(companies);
    }

}
