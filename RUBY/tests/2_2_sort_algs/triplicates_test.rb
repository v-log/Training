require_relative '../../lib/2_2_merge_sort/triplicates'
require 'minitest/autorun'

class TriplicatesTest < Minitest::Test
  def setup
    @lists = gen_lists.freeze
    list_1 = ["one", "two", "three"]
    list_2 = ["four", "five", "six"]
    list_3 = ["seven", "eight", "nine"]
    @lists_without_triplicates = [list_1, list_2, list_3].freeze
  end

  def gen_lists(list_qnt = 3, list_size = 10, item_length = 5)
    range = ('a'..'z').to_a
    same_item = 'samew'
    lists = Array.new(list_qnt).map { |list| list = Array.new(list_size).map { |item| item = Array.new(item_length) { range.sample }.join } }
    lists.map { |list| list.insert(rand(list.length), same_item) }
  end

  def test_triplicates_with_random_lists
    assert_equal "samew", triplicates(*@lists), "Triplicate must be \"samew\""
  end

  def test_tripliplicates_on_lists_without_triplicate
    assert_nil triplicates(*@lists_without_triplicates), "There must be no triplicate"
  end
end
