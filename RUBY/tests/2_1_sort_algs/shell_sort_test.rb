require 'minitest/autorun'
require_relative '../../lib/2_1_sort_algs/shell_sort'

class ShellSortTest < Minitest::Test
  def setup
    @unsorted_ary = [5,3,2,1,4,9,6,8,7]
    @sorted_ary = [1,2,3,4,5,6,7,8,9].freeze
    @unsorted_hash = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
    @sorted_hash = [['Petya', 3], ['Kolya', 2], ['Vasya', 1]].freeze
  end

  # Testing #sorted?
  def test_sorted
    assert sorted?(@sorted_ary), "Should be sorted"
  end

  def test_not_sorted
    assert !sorted?(@unsorted_ary), "Shouldn't be sorted"
  end

  # Testing #shell_sort!
  def test_shell_sort
    assert_equal @sorted_ary, shell_sort!(@unsorted_ary), "Array hasn't been sorted"
  end

  def test_shell_sort_with_random_ary
    rand_ary_1 = (0...100).to_a.shuffle
    rand_ary_2 = rand_ary_1.dup
    assert_equal rand_ary_1.sort, shell_sort_2!(rand_ary_2), "Array hasn't been sorted properly"
  end

  # Testing #shell_sort_2!
  def test_shell_sort_2_without_block
    assert_equal @sorted_ary, shell_sort_2!(@unsorted_ary), "Array hasn't been sorted"
  end

  def test_shell_sort_2_without_block_with_rand_ary
    rand_ary_1 = (0...100).to_a.shuffle
    rand_ary_2 = rand_ary_1.dup
    assert_equal rand_ary_1.sort, shell_sort_2!(rand_ary_2), "Array hasn't been sorted properly"
  end

  def test_shell_sort_2_with_block
    shell_sort_2!(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash, "Array hasn't been sorted"
  end

  def test_shell_sort_2_with_block_with_rand_ary
    permutation = (0...100).to_a.shuffle
    pairs = (0...permutation.size).map { |i| [i, permutation[i]] }
    pairs_copy = pairs.dup
    pairs.sort_by! { |item| -item[1] }
    shell_sort_2!(pairs_copy) { |item| -item[1] }
    assert_equal pairs, pairs_copy, "Array hasn't been sorted properly"
  end
end
