def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def insertion_sort!(a)
  n = a.size
  (1...n).each do |i|
    j = i
    while j > 0 && a[j] <= a[j - 1]
      a[j], a[j - 1] = a[j - 1], a[j]
      j -= 1
    end
  end
end

def insertion_sort_2!(a, lo = 0, hi = a.size - 1, aux = nil)
  get_var = block_given? ? lambda { |item| yield(item) } : lambda { |item| item }
  (lo + 1..hi).each do |i|
    j = i
    while j > lo && get_var[a[j]] <= get_var[a[j - 1]]
      a[j], a[j - 1] = a[j - 1], a[j]
      j -= 1
    end
  end

  if aux != nil
    (lo..hi).each do |index|
      aux[index] = a[index]
    end
  end
end

a = [5,3,2,1,4]
puts sorted?(a)
insertion_sort_2!(a)
raise unless sorted?(a)
puts a.inspect

b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
insertion_sort_2!(b) { |item| -item[1] }
puts b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
