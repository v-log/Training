require 'minitest/autorun' 

module SortTestModule
  def setup
    @sorted_ary = [1,2,3,4,5,6,7,8,9]
    @unsorted_ary = [6,2,8,3,1,5,4,9,7]
    @ary_sizes = [0,1,2,3,4,7,8,9,15,16,17,31,32,33,63,64,65].freeze
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
    raise NotImplementedError, "To be implemented in child test module"
  end

  def test_sort_like_without_block_with_rand_ary
    @ary_sizes.each do |ary_size|
      rand_ary_1 = (0..ary_size).to_a.shuffle
      rand_ary_2 = rand_ary_1.dup
      assert_equal rand_ary_1.sort, sort!(rand_ary_2),
      "Array hasn't been sorted properly\n #{rand_ary_2.inspect}"
    end
  end

  def test_sort_with_block
    sort!(@unsorted_hash) { |item| -item[1] }
    assert_equal @sorted_hash, @unsorted_hash, 
    "Hash hasn't been sorted properly\n #{@unsorted_hash.inspect}\n block = { |item| -item[1] }"
  end

  def test_sort_with_block_with_rand_ary
    permutation = (0...100).to_a.shuffle
    pairs = (0...permutation.size).map { |i| [i, permutation[i]] }
    pairs_copy = pairs.dup
    pairs.sort_by! { |item| -item[1] }
    sort!(pairs_copy) { |item| -item[1] }
    assert_equal pairs, pairs_copy,
    "Array hasn't been sorted properly\n #{pairs_copy.inspect}\n block = { |item| -item[1] }"
  end
end
