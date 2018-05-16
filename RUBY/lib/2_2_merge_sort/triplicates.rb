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

def min_with_nils(list)
  min = ""
  list.each do |elem|
    if elem == nil
      next
    else
      min = elem
      break
    end
  end  

  list.each do |elem|
    next if elem == nil
    min = elem if elem < min
  end
  return min if min != ""
  nil
end

# Having all lists sorted, we take first
# elements of each list, find min element;
# in the list with min element move on to
# the next element getting next trinity of
# elements, where we find min element, and
# so on, until we get 3 equal min elements.

# To eliminate the case, when there are
# several equal elements (which might be
# defined as part of triplicates), there
# is a skipping stage, checking if next
# element after min is equal to min.
def find_triplicates(given_lists)
  lists_total_size = given_lists.map(&:size).sum
  # indices of all lists to move through them
  indices = Array.new(given_lists.size, 0)
  # equal elements counter - inc when coincidence found
  # (1  - for current element)
  eql_elems = 1
  prev_min = ""
  
  lists_total_size.times do
    # Take first elements of sorted lists
    # and find min out of those
    curr_min_elems = Array.new

    given_lists.each_with_index do |list, list_number|
      if indices[list_number] < list.size
        curr_min_elems << list[indices[list_number]]
      else
        curr_min_elems << nil
      end
    end

    puts "curr_min_elems = #{curr_min_elems}"

    # Current min element out of all lists
#    curr_min = curr_min_elems.min
    curr_min = min_with_nils(curr_min_elems)
    return nil if curr_min == nil
    puts "curr_min = #{curr_min}"
    
    # In a list with min element found inc the index
    # while elements after min are equal to min
    # (to eliminate counting copies in one list as
    # a part of triplicate)
=begin
    given_lists.each_with_index do |list, list_number|
      list_elem = list[indices[list_number]]
      
      if list_elem == curr
        indices[list_number] += 1
        break
      end
    end
=end

    curr_min_elems.each_with_index do |elem, list_number|
      next if elem == nil
#      puts "continue"
      if elem == curr_min
        list = given_lists[list_number]
        index = indices[list_number]

        puts "list with min elem = #{list_number}"

#        indices[list_number] += 1
#        next_elem = list[index + 1]

        while list[indices[list_number]] == curr_min do
#              indices[list_number] == list.size - 1 do
          indices[list_number] += 1
        end

        break
      end
    end

    # Count equal elements    
    if curr_min == prev_min
      eql_elems += 1
    else
      eql_elems = 1
    end

    prev_min = curr_min

    # Exit when first three equal elements
    # in different arrays are found
    return curr_min if eql_elems == 3
  end

  # If no triplicates found
  nil
end

a = ["abcd", "bcde", "cdef"]
b = ["cdef", "cdef", "efgh"]
c = ["aghi", "bhij", "ckef"]

p a.inspect
p b.inspect
p c.inspect
puts
p "triplicate = #{triplicates(a, b, c)}"
