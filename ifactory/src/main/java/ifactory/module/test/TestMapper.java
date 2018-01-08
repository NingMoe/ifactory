package ifactory.module.test;



import java.util.List;

public interface TestMapper {
    void insert(TestModel model);
    void delete(TestModel model);
    void update(TestModel model);
    List<TestModel> selects(TestModel model);
    TestModel select(TestModel model);
}
