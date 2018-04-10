require_relative 'merge_sort'

def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

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

def total_size(lists)
  total_size = 0
  lists.each do |list|
    total_size += list.size
  end
  total_size
end

def find_triplicates(given_lists)
  lists_total_size = total_size(given_lists)
  indices = Array.new(given_lists.size, 0)
  eql_elems = 0
  prev = ""
  
  lists_total_size.times do |curr|
    # look through all lists finding min element
    curr_min_elems = Array.new
    given_lists.each_with_index do |list, list_number|
      if indices[list_number] < list.size
        curr_min_elems << list[indices[list_number]]
      end
    end
    
    curr = curr_min_elems.min
    
    # inc index in a list with min elem
    given_lists.each_with_index do |list, list_number|
      list_elem = list[indices[list_number]]
      if list_elem.eql?(curr)
        indices[list_number] += 1
        break
      end
    end
    
    if curr.eql?(prev)
      eql_elems += 1
    else
      eql_elems = 0
    end

    prev = curr
    
    return curr if eql_elems == 2
  end

  nil
end

a = ["abcd", "bcde", "cdef"]
b = ["cdef", "defg", "efgh"]
c = ["aghi", "bhij", "cdef"]
#d = triplicates(a, b, c)
puts "triplicate = #{triplicates(a, b, c)}"
