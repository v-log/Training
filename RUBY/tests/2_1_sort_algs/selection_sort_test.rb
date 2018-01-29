require 'minitest/autorun'
require_relative '../../lib/2_1_SortAlgs/selection_sort'

class SelectionSortTest < Minitest::Test
  def setup
    @unsorted_ary = [5,3,2,1,4]
    @sorted_ary = [1,2,3,4,5].freeze
    @unsorted_hash = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
    @sorted_hash = [['Petya', 3], ['Kolya', 2], ['Vasya', 1]].freeze
  end

  # Testing #sorted?
  def test_sorted
    assert sorted?(@sorted_ary), "Should be sorted"
  end

  def test_not_sorted
    refute sorted?(@unsorted_ary), "Shouldn't be sorted"
  end

  # Testing #selection_sort
  def test_selection_sort
    assert_equal @sorted_ary, selection_sort!(@unsorted_ary)
  end

  def test_selection_sort_with_random_ary
    rand_ary_1 = (0...100).to_a.shuffle
    rand_ary_2 = rand_ary_1.dup
    assert_equal rand_ary_1.sort, selection_sort!(rand_ary_2)
  end

  # Testing #selection_sort2
  def test_selection_sort2_without_block
    assert_equal @sorted_ary, selection_sort2!(@unsorted_ary)
  end

  def test_selection_sort2_without_block_with_rand_ary
    rand_ary_1 = (0...100).to_a.shuffle
    rand_ary_2 = rand_ary_1.dup
    assert_equal rand_ary_1.sort, selection_sort2!(rand_ary_2)
  end

  def test_selection_sort2_with_block
    selection_sort2!(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash
  end

  def test_selection_sort2_with_block_with_rand_ary
    permutation = (0...100).to_a.shuffle
    pairs = []
    (0...permutation.size).each { |i| pairs.push [i, permutation[i]] }
    pairs_copy = pairs.dup
    pairs.sort_by! { |item| -item[1] }
    selection_sort2!(pairs_copy) { |item| -item[1] }
    assert_equal(pairs, pairs_copy)
  end
end
