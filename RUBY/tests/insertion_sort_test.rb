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
    refute sorted?(@unsorted_arr), "Shouldn't be sorted"
  end

  # Testing #insertion_sort
  def test_insertion_sort
    assert_equal @sorted_arr, insertion_sort(@unsorted_arr)
  end

  def test_insertion_sort_with_random_array
    rand_arr_1 = Array.new(100) { rand(0...99) }
    rand_arr_2 = rand_arr_1[0..rand_arr_1.size]
    assert_equal rand_arr_1.sort, insertion_sort(rand_arr_2)
  end

  # Testing #insertion_sort2
  def test_insertion_sort2_without_block
    assert_equal @sorted_arr, insertion_sort2(@unsorted_arr)
  end

  def test_insertion_sort2_without_block_with_rand_arr
    rand_arr_1 = Array.new(100) { rand(0..99) }
    rand_arr_2 = rand_arr_1[0..rand_arr_1.size]
    assert_equal rand_arr_1.sort, insertion_sort2(rand_arr_2)
  end

  def test_insertion_sort2_with_block
    insertion_sort2(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash
  end

  def test_insertion_sort2_with_block_with_rand_arr
    rand_hash_1 = {}
    shuffled_indices = (0..99).to_a.shuffle
    100.times { |n| rand_hash_1[n] = shuffled_indices.pop }
    rand_hash_2 = rand_hash_1.clone.to_a

    insertion_sort2(rand_hash_2) { |item| -item[1] }
    assert_equal(rand_hash_1.sort_by { |key, value| -value }, rand_hash_2)
  end
end
