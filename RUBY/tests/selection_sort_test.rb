require 'minitest/autorun'
require_relative '../lib/2_1_SortAlgs/selection_sort'

class SelectionSortTest < Minitest::Test
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
    refute sorted?(@unsorted_arr), "Shouldn't be sorted"
  end

  # Testing #selection_sort
  def test_selection_sort
    assert_equal @sorted_arr, selection_sort!(@unsorted_arr)
  end

  def test_selection_sort_with_random_array
    rand_arr_1 = Array.new(100) { rand(0...99) }
    rand_arr_2 = rand_arr_1[0..rand_arr_1.size]
    assert_equal rand_arr_1.sort, selection_sort!(rand_arr_2)
  end

  # Testing #selection_sort2
  def test_selection_sort2_without_block
    assert_equal @sorted_arr, selection_sort2!(@unsorted_arr)
  end

  def test_selection_sort2_without_block_with_rand_arr
    rand_arr_1 = (0...100).to_a.shuffle
    rand_arr_2 = rand_arr_1.dup
    assert_equal rand_arr_1.sort, selection_sort2!(rand_arr_2)
  end

  def test_selection_sort2_with_block
    selection_sort2!(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash
  end

  def test_selection_sort2_with_block_with_rand_arr
    permutation = (0...100).to_a.shuffle
    pairs = []
    (0...permutation.size).each { |i| pairs.push [i, permutation[i]] }
    pairs_copy = pairs.dup
    pairs.sort_by! { |item| -item[1] }
    selection_sort2!(pairs_copy) { |item| -item[1] }
    assert_equal(pairs, pairs_copy)
  end
end
