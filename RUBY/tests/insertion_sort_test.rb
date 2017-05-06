require 'minitest/autorun'
require_relative '../lib/2_1_SortAlgs/insertion_sort'

class InsertionSortTest < Minitest::Test
  def setup
    @unsorted_arr = [5,3,2,1,4]
    @sorted_arr = [1,2,3,4,5].freeze
    @unsorted_hash = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
    @sorted_hash = [['Petya', 3], ['Kolya', 2], ['Vasya', 1]].freeze
  end

  # Testing #sorted?
  def test_sorted
    assert sorted?(@sorted_arr), "Should be sorted"
  end

  def test_not_sorted
    assert !sorted?(@unsorted_arr), "Shouldn't be sorted"
  end

  # Testing #insertion_sort
  def test_insertion_sort
    assert_equal @sorted_arr, insertion_sort(@unsorted_arr)
  end

  # Testing #insertion_sort2
  def test_insertion_sort2_without_block
    assert_equal @sorted_arr, insertion_sort2(@unsorted_arr)
  end

  def test_insertion_sort2_with_block
    insertion_sort2(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash
  end
end
