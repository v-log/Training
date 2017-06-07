def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def shell_sort!(a)
  n = a.size
  h = 1
  h = 3 * h + 1 while h < n / 3

  while h >= 1
    (h...n).each do |i|
      j = i
      while j >= h && a[j] < a[j - h]
        a[j], a[j - h] = a[j - h], a[j]
        j -= h
      end
    end

    h = h / 3
  end
  a
end

def shell_sort_2!(a)
  n = a.size
  h = 1
  h = 3 * h + 1 while h < n / 3

  get_var = block_given? ? lambda { |item| yield(item) } : lambda { |item| item }

  while h >= 1
    (h...n).each do |i|
      j = i
      while j >= h && get_var[a[j]] < get_var[a[j - h]]
        a[j], a[j - h] = a[j - h], a[j]
        j -= h
      end
    end

    h = h / 3
  end
  a
end

a = [5,3,2,1,4,9,6,8,7]
puts sorted?(a)
shell_sort!(a)
raise unless sorted?(a)
puts a.inspect

b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
shell_sort_2!(b) { |item| -item[1] }
puts b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
