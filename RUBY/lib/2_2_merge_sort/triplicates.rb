def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def less(a, b)
  return a if a < b
  b
end

def triplicates(*lists)
  lists_size = 0
  lists.each do |x|
    lists_size += x.size
  end
  
  indexes = Array.new(lists.size, 0)
  k = 0
  cur = ""
  lists_size.times do |x|
    temp = Array.new
    lists.each_with_index do |item, index|
      if indexes[index] < item.size
        temp << item[indexes[index]]
      end
    end
    
    x = min(temp)
    lists.each_with_index do |z, index|
      if z[indexes[index]].eql?(x)
        indexes[index] += 1
        break
      end
    end
    
    if x.eql?(cur)
      k += 1
    else
      k = 0
    end
    
    cur = x
    return x if k == 2
  end

  nil
end

private

def min(p)
  min = p[0]
  p.each do |x|
    min = x if x < min
  end
  
  min
end

a = ["abcd", "bcde", "cdef"]
b = ["cdef", "defg", "efgh"]
c = ["aghi", "bhij", "cdef"]
puts "triplicate = #{triplicates(a, b, c)}"
