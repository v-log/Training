require_relative 'merge_sort'

# Defines if 'a' is sorted
def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

# General idea is to find three equal elements
# (triplicate), one per list, by sorting each list
# and looking through trinities of lists' minimal
# elements one by one, until we find first trinity
# with all elements being equal.
def triplicates(*lists)
  return nil if lists.size <= 1

  # sort input lists
  lists.each do |list|
    merge_sort!(list)
  end
  
  # find_triplicates
  find_triplicates(lists)
end 

private

# Having all lists sorted, we take first
# elements of each list.................
def find_triplicates(given_lists)
  lists_total_size = given_lists.map(&:size).sum
  # indices of all lists to move through them
  indices = Array.new(given_lists.size, 0)
  # equal elements counter - inc when coincidence found
  # (1  - for current element)
  eql_elems = 1
  prev = ""
  
  lists_total_size.times do |curr|
    # Take first elements of sorted lists
    # and find min out of those
    curr_min_elems = Array.new
    given_lists.each_with_index do |list, list_number|
      if indices[list_number] < list.size
        curr_min_elems << list[indices[list_number]]
      end
    end
    
    curr = curr_min_elems.min
    
    # In a list with min element found inc an index
    given_lists.each_with_index do |list, list_number|
      list_elem = list[indices[list_number]]
      if list_elem == curr
        indices[list_number] += 1
        break
      end
    end
    
    if curr == prev
      eql_elems += 1
    else
      eql_elems = 1
    end

    prev = curr

    # Exit when first three equal elements
    # in different arrays are found
    return curr if eql_elems == 3
  end

  # nil, if no triplicates found
  nil
end

a = ["abcd", "bcde", "cdef"]
b = ["cdef", "cdef", "efgh"]
c = ["aghi", "bhij", "ckef"]
puts "triplicate = #{triplicates(a, b, c)}"
