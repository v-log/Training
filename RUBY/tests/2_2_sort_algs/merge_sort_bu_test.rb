require_relative 'sort_test'
require_relative '../../lib/2_2_merge_sort/merge_sort'
class MergeSortTest < Minitest::Test
  include SortTestModule
  
  def sort!(a, &block)
    merge_sort_bu!(a, &block)
  end
end
