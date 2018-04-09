require 'minitest/autorun' 

module SortTestModule
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

  # Testing #sort!
  def sort!()
    raise NotImplementedError, "To be implemented in child class"
  end

  def test_sort_like_without_block
      assert_equal @sorted_ary, sort!(@unsorted_ary) { |x| x}, "Array hasn't been sorted"
  end

  def test_sort_like_without_block_with_rand_ary
    rand_ary_1 = (0...100).to_a.shuffle
    rand_ary_2 = rand_ary_1.dup
    assert_equal rand_ary_1.sort, sort!(rand_ary_2) { |x| x }, "Array hasn't been sorted properly"
  end

  def test_sort_with_block
    sort!(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash, "Array hasn't been sorted"
  end

  def test_sort_with_block_with_rand_ary
    permutation = (0...100).to_a.shuffle
    pairs = (0...permutation.size).map { |i| [i, permutation[i]] }
    pairs_copy = pairs.dup
    pairs.sort_by! { |item| -item[1] }
    sort!(pairs_copy) { |item| -item[1] }
    assert_equal pairs, pairs_copy, "Array hasn't been sorted properly"
  end
end
