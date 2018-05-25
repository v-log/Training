require_relative '../../lib/2_2_merge_sort/triplicates'
require 'minitest/autorun'

class TriplicatesTest < Minitest::Test
  def setup
    list_1 = ["one", "two", "three"]
    list_2 = ["four", "five", "six"]
    list_3 = ["seven", "eight", "nine"]
    @lists_without_triplicates = [list_1, list_2, list_3]
    @random_lists = gen_lists
    @single_list = ["aaaa", "bbbb", "cccc", "dddd"]
  end

  def gen_lists(list_qnt = 4, max_list_size = 10, max_item_length = 7)
    range = ('a'..'z').to_a
    common_item = 'common'
    lists = Array.new(list_qnt).map { |list| list = Array.new(5 + rand(max_list_size - 5)).map { |item| item = Array.new(3 + rand(max_item_length - 3)) { range.sample }.join } }
    lists.map { |list| list.insert(rand(list.length - 1), common_item) }
  end

  def test_common_elem_with_random_lists
    @random_lists.each do |list|
      p list.inspect
    end
    assert_equal "common", common_elem(*@random_lists), "Triplicate must be \"common\""
  end

  def test_common_elem_on_lists_without_one
    assert_nil common_elem(*@lists_without_common_elem), "There must be no common element"
  end

  def test_common_elem_with_single_list
    assert_nil common_elem(@single_list), "There must be no common element"
  end
end
