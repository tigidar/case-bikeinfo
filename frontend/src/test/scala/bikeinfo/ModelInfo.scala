package bikeinfo

class ModelTest extends munit.FunSuite:
  test("fullPrice") {
    val item = DataItem(DataItemID(), "test", 0.5, 5)
    assert(item.fullPrice == 2.5)
  }
end ModelTest

